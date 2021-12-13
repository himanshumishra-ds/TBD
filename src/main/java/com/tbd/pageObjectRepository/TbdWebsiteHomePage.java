package com.tbd.pageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TbdWebsiteHomePage {

	
	@FindBy(xpath="//li[text()='Halal']") private WebElement foodtype;
	
	
	
	public TbdWebsiteHomePage(WebDriver driver )
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getFoodtype() {
		return foodtype;
	}



	
	
//	
	
}
