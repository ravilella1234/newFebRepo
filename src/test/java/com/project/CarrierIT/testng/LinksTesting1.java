package com.project.CarrierIT.testng;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.CarrierIT.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class LinksTesting1 extends BaseTest
{

	@BeforeMethod(groups = {"regression","sanity"})
	@Parameters("browser")
	public void setup(String bType) throws Exception
	{
		init();
		test=r.startTest("LinksTesting1");
		test.log(LogStatus.INFO, "Initializing the Properties Files....");
		
		launch(bType);
		test.log(LogStatus.INFO, "Opened the browser :-" + bType);
		
		navigateUrl("googleurl");
		test.log(LogStatus.INFO, "Navigated to url :-" + eprop.getProperty("googleurl"));
	}
	
	
	
	
	@Test(groups = {"regression","sanity"})
	public void linktesting1()
	
	 {
		String expval="Google Images";
		
		driver.findElement(By.linkText("Images")).click();	
		Reporter.log("Clicked on Image Link");
		String actval=driver.getTitle();
		
		Assert.assertEquals(actval, expval);
		
	 }
	
	@AfterMethod(groups = {"regression","sanity"})
	public void tearDown()
	{
		driver.quit();
		
		   r.endTest(test);
			r.flush();
	}

}
