package com.tbd.test_scripts;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tbd.pageObjectRepository.TbdWebsiteHomePage;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.OutputStream;

public class PdfVerify {
	int rowNo =1;
	//
	@Test
	public void Pdftest() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.socialaudit.rajasthan.gov.in/");
		TbdWebsiteHomePage websiteHomePage = new TbdWebsiteHomePage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//a[text()='Research & Reports']")).click();
		driver.findElement(By.xpath(
				"//a[text()='Research & Reports']/following-sibling::ul/li/a[text()=' Concurrent Social Audit Reports ']"))
				.click();

		Select s1 = new Select(driver.findElement(By.xpath("//select[@name='district']")));
		ArrayList<WebElement> cities = new ArrayList<WebElement>(s1.getOptions());

		Select s2 = new Select(driver.findElement(By.xpath("//select[@name='year']")));
		ArrayList<WebElement> years = new ArrayList<WebElement>(s2.getOptions());

		Select s3 = new Select(driver.findElement(By.xpath("//select[@name='month']")));
		ArrayList<WebElement> months = new ArrayList<WebElement>(s3.getOptions());

		for (int city = 0; city < cities.size(); city++) {
			s1.selectByVisibleText(cities.get(city).getText());

			for (int year = 0; year < years.size(); year++) {
				s2.selectByVisibleText(years.get(year).getText());

				for (int month = 0; month < months.size(); month++) {

					s3.selectByVisibleText(months.get(month).getText());
					driver.findElement(By.xpath("//button[text()='Get Reports']")).click();
					try {
						
						(new WebDriverWait(driver, Duration.ofSeconds(1)))
								.until(ExpectedConditions.numberOfWindowsToBe(2));
						//System.out.println(driver.getWindowHandles().size());
						String oldTab = driver.getWindowHandle();

						ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
						newTab.remove(oldTab);
						// change focus to new tab
						driver.switchTo().window(newTab.get(0));
						// Do what you want here, you are in the new tab
						String pdfUrl = driver.getCurrentUrl();
						String newPdfUrl = pdfUrl.replaceAll("https", "http");
						System.out.println(newPdfUrl);						
						System.setProperty("http.agent", "Chrome");
						URL url = new URL(newPdfUrl);
						InputStream is = url.openStream();
						BufferedInputStream fileParse = new BufferedInputStream(is);
						PDDocument document = null;
						document = PDDocument.load(fileParse);
						String pdfContent = new PDFTextStripper().getText(document);
						//Assert.assertTrue(pdfContent.contains("any String that you want to match"));
						//System.out.println(pdfContent);
						//Thread.sleep(3000);
						driver.close();
						//change focus back to old tab
						driver.switchTo().window(oldTab);
					} catch (Exception e) {
						System.out.println(cities.get(city).getText() + " " + years.get(year).getText() + " " + months.get(month).getText());
						
						//Writing data into excel sheet  
						
						FileInputStream fis=new FileInputStream("./pdfverify.xlsx");
						Workbook wb=WorkbookFactory.create(fis);
						String data = cities.get(city).getText() + " " + years.get(year).getText() + " " + months.get(month).getText();
						wb.getSheet("ssaat").createRow(rowNo).createCell(1).setCellValue(data);
						//wb.getSheet("Sheet1").getRow(2).createCell(5).setCellValue("oooooSuccessfully Written By WriteDataIntoExcelSheet class ");
						rowNo++;
						FileOutputStream fos=new FileOutputStream("./pdfverify.xlsx");
						wb.write(fos);
						
						if (driver.getWindowHandles().size() == 1) {
							
							
							continue;
						}

					}
					if(cities.size()==city)
					{
						break;
					}
				}
			}

			System.out.println("Checking for City name : " + cities.get(city+1).getText());
			//Thread.sleep(6000);
		}

	}

}
