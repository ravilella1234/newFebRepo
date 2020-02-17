package com.project.CarrierIT.testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.CarrierIT.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class RadioButton1 extends BaseTest
{


	@BeforeMethod(groups = {"regression","smoke"})
	@Parameters("browser")
	  public void beforeMethod(String bType) throws Exception 
	  {
		init();
		test=r.startTest("RadioButton1");
		test.log(LogStatus.INFO, "Initializing the Properties Files....");
		
		launch(bType);
		test.log(LogStatus.INFO, "Opened the browser :-" + bType);
		
		navigateUrl("radiourl");
		test.log(LogStatus.INFO, "Navigated to url :-" + eprop.getProperty("radiourl"));
			
	  }
	
	
	@Test(groups = {"regression","smoke"})
	public void radioTest()
	{
		WebElement radio=driver.findElement(By.xpath("(//td[@class='table5'])[2]"));
		List<WebElement> rbutton=radio.findElements(By.name("group1"));
		for(int i=0;i<rbutton.size();i++)
		{
			System.out.println(rbutton.get(i).getAttribute("value")+ "----" + rbutton.get(i).getAttribute("checked"));
		}
	}
 

  @AfterMethod(groups = {"regression","smoke"})
  public void afterMethod() 
  {
	  driver.quit();
	  
	  r.endTest(test);
		r.flush();
  }
	
}
