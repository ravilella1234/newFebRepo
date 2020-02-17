package com.excels;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.CarrierIT.BaseTest;

public class FaceBook extends BaseTest
{
	ExcelAPI e;
	
	@BeforeTest
	@Parameters("browser")
	public void startProcess(String bType) throws Exception
	{
		init();
		launch(bType);
		navigateUrl("fburl");
		e=new ExcelAPI("C:\\Users\\DELL\\Desktop\\testdata.xlsx");
	}
	
	@Test
	public void fb() throws Exception 
	{
		for(int i=1;i<e.getRowCount("login");i++)
		{
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(e.getCellData("login", "UserName", i));
			
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(e.getCellData("login", "Password", i));
			
			Thread.sleep(3000);
		}
		

	}

}
