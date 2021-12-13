package com.tbd.test_scripts;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaStreamsExample {

	@Test
	public void javaStreamsExample() {

		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		List<WebElement> ele = driver.findElements(By.xpath("//tbody/tr/td[1]"));
		List<String> eleList = ele.stream().map(s -> s.getText()).sorted().collect(Collectors.toList());
		System.out.println(eleList);

		List<String> sortedEleList = eleList.stream().sorted().collect(Collectors.toList());
		System.out.println(sortedEleList);
		Assert.assertTrue(eleList.equals(sortedEleList));

		// -------------------------------------------------------
		List<String> price;
		do {
			List<WebElement> elements = driver.findElements(By.xpath("//tbody/tr/td[1]"));
			price = elements.stream().filter(s -> s.getText().equals("Beans")).map(s -> getVeggiPrice(s))
					.collect(Collectors.toList());
			if(price.size()==0)
			{
				driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
			}
			
		}while(price.size()<1);
		
		System.out.println(price);
	}

	private String getVeggiPrice(WebElement veggie) {

		String price = veggie.findElement(By.xpath("following-sibling::td[1]")).getText();
		return price;
	}
	
	@Test
	public void autoSearch() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		/*
		//Task:- verify auto search results. are they contain searched item or not
		//Solution 1 , through traditional method
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		String item="ch";
		driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys(item);
		List<WebElement> items =driver.findElements(By.xpath("//tbody/tr/td[1]"));
		if(items.size()==0)
		{
			System.out.println("No Item Found");
		}
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getText().toLowerCase().contains(item))
			{
				continue;
			}
			else
			{
				System.out.println("Search result "+items.get(i).getText()+"does not contain "+item);
				break;
			}
			
		}
		*/
		//Task:- verify auto search results. are they contain searched item or not
		//Solution 2 through Java Streams
		
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		String item2="ch";
		driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys(item2);
		//Thread.sleep(3000);
		List<WebElement> searchedItems =driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> a = searchedItems.stream().filter(s->s.getText().toLowerCase().contains("ch")).collect(Collectors.toList());
		System.out.println(searchedItems);
		System.out.println(a);
		Assert.assertEquals(searchedItems.size(), a.size());
		
		
		
	}
	
	

}
