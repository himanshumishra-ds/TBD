package com.tbd.test_scripts;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeReading {
    @Test
	public void BarcodeRead() throws NotFoundException, IOException, InterruptedException
	{
    	System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
    // chrome	headless mode
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1400,800");       
        options.addArguments("disable-gpu");
    	WebDriver driver = new ChromeDriver(options);
		driver.get("http://172.104.160.243:8080/konexposvendor/login");
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("testvendor@mailinator.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("1234567");
		driver.findElement(By.xpath("//button[@type='submit']")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//span[contains(text(),'Products')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		Thread.sleep(5000);
		String src =driver.findElement(By.xpath("//tbody/tr[1]/td[7]/img")).getAttribute("src");
		System.out.println("image1 url is : "+src);
		URL urlOfImage = new URL(src);

//<!-- for reading QR code in Java, we need ZXing jars -//in pom.xml we added->
		
        //reading BAR CODE
		BufferedImage bufferedImage = ImageIO.read(urlOfImage);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		String textPresentInImage = result.getText();
		System.out.println("Text Present in Image : "+textPresentInImage);
		Assert.assertEquals(textPresentInImage, "309520932145096");
		
		//READING QR CODE
		
		driver.findElement(By.xpath("//span[text()='Customers Management']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='CustomerList']")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).click();
		String src1 =driver.findElement(By.xpath("//div[@class='qr_code']/img")).getAttribute("src");
		System.out.println("image2 url is : "+src1);
		URL urlOfImage1 = new URL(src1);
		
		BufferedImage bufferedImage1 = ImageIO.read(urlOfImage1);
		LuminanceSource source1 = new BufferedImageLuminanceSource(bufferedImage1);
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source1));
		Result result1 = new MultiFormatReader().decode(bitmap1);
		String textPresentInImage1 = result1.getText();
		System.out.println("Text Present in Image Qr code : "+textPresentInImage1);
		Assert.assertEquals(textPresentInImage1, "27763528456948");
		
		
		
	}
	
	
	
}
