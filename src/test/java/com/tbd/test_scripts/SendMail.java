package com.tbd.test_scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Test;

public class SendMail {

	@Test
	public void sendMail() throws AWTException, InterruptedException, EncryptedDocumentException, IOException {
		//System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		 ; ;

		ChromeOptions options = new ChromeOptions();		
		options.addArguments("user-data-dir=C:\\Users\\HIMANSHU MISHRA\\AppData\\Local\\Google\\Chrome\\User Data\\");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		//options.setExperimentalOption("debuggerAddress","localhost:9222");
      	WebDriver driver = new ChromeDriver(options);
		driver.get("https://gmail.com");
		FileInputStream fis = new FileInputStream("./JobEmailId.xlsx");
		Workbook wb=	WorkbookFactory.create(fis);
		int lastRowCount =wb.getSheet("newjobs").getLastRowNum();
		System.out.println(lastRowCount);
		for(int i=0;i<2;i++)
		{
			driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//img[@aria-label='Full screen (Shift for pop-out)']")).click();
			String emailId=wb.getSheet("newjobs").getRow(i).getCell(0).getStringCellValue();
			
			
			driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(emailId);
			
			driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Job Application for QA profile");
			driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("Dear Hiring Manager,\r\n"
					+ "\r\n"
					+ "I am submitting my resume for the position of QA Tester. This unsolicited cover letter is to show my deep interest to work in your company. As a skilled and highly educated professional with 2.8 years of experience in testing Web-based applications, I am confident of my ability to make a significant contribution to your organization.\r\n"
					+ "My professional experience includes analyzing the business requirements, creating test strategies, and executing tests to identify bugs in new software and create and maintain automation packs for the same.\r\n"
					+ "\r\n"
					+ "With my educational background and my attention to detail and performance, I am in an excellent position to help your organization to produce top-quality software products.  \r\n"
					+ "The following are some Skills and highlights of my qualifications and experience: \r\n"
					+ "\r\n"
					+ "- Working experience on Selenium WebDriver using Java for UI testing. \r\n"
					+ "- Working Knowledge of tools like MantisBT, JIRA, Hp ALM, and Hp QC \r\n"
					+ "- Experience in Maven, Jenkins \r\n"
					+ "- UI Testing \r\n"
					+ "- Experienced all phases of Agile and Waterfall models. \r\n"
					+ "- Frameworks using TestNG (Page object Model, Data-Driven, Page Factory, etc. ) \r\n"
					+ "- Git and GitHub knowledge. \r\n"
					+ "- Blackbox Testing (System, System Integration, Smoke, Sanity, Regression, Ad-hoc) \r\n"
					+ "- Complete knowledge of SDLC and STLC and Bug life cycle and Test Case creation. \r\n"
					+ "- Team Leading experience \r\n"
					+ "- Basic Knowledge of Rest API(Postman)\r\n"
					+ "\r\n"
					+ "Kind Regards,\r\n"
					+ "Himanshu");
			driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
		//uploading the file 
			StringSelection ss = new StringSelection("C:\\Users\\HIMANSHU MISHRA\\Desktop\\himanshu Testing resume\\2.8\\Himanshu_QA_Automation_Pdf.pdf");
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		   //pasting file path in windows popup
		    Robot robot = new Robot();
		    robot.setAutoDelay(1000);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    
		    Thread.sleep(2000);
		    //=====
		    driver.findElement(By.xpath("//div[@class='a1 aaA aMZ aF2']")).click();
			//uploading the file 
				StringSelection ss1 = new StringSelection("C:\\Users\\HIMANSHU MISHRA\\Desktop\\himanshu Testing resume\\2.8\\Himanshu_QA_Cover Letter_word.docx");
			    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);
			   //pasting file path in windows popup
			    
			    robot.setAutoDelay(1000);
			    robot.keyPress(KeyEvent.VK_CONTROL);
			    robot.keyPress(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_CONTROL);
			    robot.keyRelease(KeyEvent.VK_V);
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
			    
			    
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@aria-label='Send ‪(Ctrl-Enter)‬']")).click();
		    
		    
			//C:\Users\HIMANSHU MISHRA\Desktop\himanshu Testing resume\2.8\Himanshu_QA_Automation_Pdf.pdf
			//C:\Users\HIMANSHU MISHRA\Desktop\himanshu Testing resume\2.8\Himanshu_QA_Cover Letter_word.docx
	        
		}
		
		
		
	}

}
