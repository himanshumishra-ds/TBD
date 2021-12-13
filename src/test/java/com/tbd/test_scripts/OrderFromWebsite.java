package com.tbd.test_scripts;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tbd.pageObjectRepository.TbdWebsiteHomePage;

public class OrderFromWebsite {

	@Test
	public void orderFromWebsite() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://45.79.124.20/tbd/");
		TbdWebsiteHomePage websiteHomePage = new TbdWebsiteHomePage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println(driver.getTitle().equals("Food Delivery"));

		List<WebElement> foodTypes = driver.findElements(By.xpath("//ul[@class='resp-tabs-list filter-menu']/li"));

		String restroname = "Pakiza-Restro";

		for (int i = 0; i < foodTypes.size(); i++)

		{

			System.out.println("Checking inside" + foodTypes.get(i).getText());

			List<WebElement> foodTypesInnerRestroList = driver
					.findElements(By.xpath("//div[@class='restra-inner']/h3"));			
			for (int j = 0; j < foodTypesInnerRestroList.size(); j++)

			{

				if (foodTypesInnerRestroList.get(j).getText().equals(restroname)) {
					System.out.println("Yes I found restro, name is :"
							+ driver.findElement(By.xpath("//h3[text()='Pakiza-Restro']")).getText()+" I am clicking on it");
					break;
				}

			}

			if ((i + 1) < foodTypes.size()) {
				foodTypes.get(i + 1).click();
			}

			Thread.sleep(1000);
		}
		driver.findElement(By.xpath("//h3[text()='Pakiza-Restro']")).click();
		driver.findElement(By.xpath("(//tbody)[1] //td/a[1]")).click();
	    driver.findElement(By.xpath("(//label[@class='checkbox-inline'])[1]/input")).click();
	    driver.findElement(By.xpath("(//a[contains(@id,'subAddtocart')])[1]/i")).click();
		  
	    int orderCount=0;
	    do {
	    	driver.findElement(By.xpath("//span[@class='qty_btn addcart']/i")).click();
	    	orderCount++;
	   }while(orderCount<1);
	    
	    driver.findElement(By.xpath("(//span[@class='checkmark'])[1]")).click();
	    Thread.sleep(7000);
	    driver.findElement(By.xpath("//a[@id='order_check']")).click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h1[text()='Login']"))));
	    driver.findElement(By.id("email")).sendKeys("8549821301");
	    driver.findElement(By.xpath("//button[text()='Login']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
	    
	    driver.findElement(By.xpath("//button[text()='Login']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Himanshu Mishr']")));
	    driver.findElement(By.xpath("(//a[@id='navbardrop']/i)[2]")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartUpdate")));
	    driver.findElement((By.xpath("//button[text()='Checkout']"))).click();
	    driver.findElement(By.xpath("//a[text()='Proceed to checkout']")).click();
	    driver.findElement(By.xpath("//button[text()='DELIVER HERE']")).click();
	   driver.findElement(By.xpath("//button[text()='PAY ']")).click();
	   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Please select currency amount']")));
	   Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Please select currency amount']")).isDisplayed(), restroname);
	    driver.findElement(By.xpath("//input[@data-type='200']")).sendKeys("10");
	    
	    driver.findElement(By.xpath("//button[text()='PAY ']")).click();
	    
	    driver.close();
	    
		
		
	}
	

	
	
}
