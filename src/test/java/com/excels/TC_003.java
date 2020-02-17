package com.excels;

import org.testng.annotations.Test;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class TC_003 
{
	WebDriver driver;
	
  @Test(dataProvider = "dp")
  public void f(Hashtable<String, String> data) 
  {
	  if(data.get("Runmode").equals("N"))
		  throw new SkipException("Run mode is set to No..");
	  	System.out.println(data.get("Browser"));
	  
	  //if(data.get("RunMode").equals("n"))
		//  throw new SkipException("Run mode set to no...");
	  
		 // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe"); 
	  		driver=new ChromeDriver();
			driver.manage().window().maximize(); 
			
			driver.get("https://www.facebook.com");	
			driver.findElement(By.id("email")).sendKeys(data.get("UserName"));
			driver.findElement(By.id("pass")).sendKeys(data.get("UserPassword"));
  }

  @DataProvider
  public Object[][] dp() throws Exception 
  {
	  ExcelAPI xls=new ExcelAPI("C:\\Users\\DELL\\Desktop\\SuiteA.xlsx");
		String sheetName="data";
		String testCaseName="LoginTest";
		
	return  DataUtils.getTestData(xls, sheetName, testCaseName);
  }
}
