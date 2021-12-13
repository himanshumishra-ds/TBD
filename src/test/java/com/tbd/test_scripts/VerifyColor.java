package com.tbd.test_scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyColor {

	@Test
	public void verifyColor()
	{
		
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.brchat.org/");
String colr = driver.findElement(By.xpath("//a[contains(text(),'  ')]"))
			      .getCssValue("color");
			      //getting background color attribute with getCssValue()
			      String bckgclr = driver.findElement(By.xpath("//a[contains(text(),'  ')]"))
			      .getCssValue("margin");
			      Assert.assertEquals(colr,"rgba(51, 122, 183, 1)");
			      
			      System.out.println(colr);
			      System.out.println(bckgclr);
	}
	
	
	
}
