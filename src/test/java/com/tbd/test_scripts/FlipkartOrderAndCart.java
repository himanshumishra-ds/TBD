package com.tbd.test_scripts;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FlipkartOrderAndCart {

	@Test
	public void flipkartOrder()
	{
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
	    
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");
		
		 driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(20) );
			
	        if(driver.findElement(By.xpath("//button[text()='✕']")).isDisplayed())
			{
				driver.findElement(By.xpath("//button[text()='✕']")).click();
			}
		
		
	}
	
	
	
	
}
