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

public class CheckBox1 extends BaseTest
{
  
	@BeforeMethod(groups = {"regression","smoke"})
	@Parameters("browser")
	  public void beforeMethod(String bType) throws Exception 
	  {
		init();
		test=r.startTest("CheckBox1");
		test.log(LogStatus.INFO, "Initializing the Properties Files....");
		
		launch(bType);
		test.log(LogStatus.INFO, "Opened the browser :-" + bType);
		
		navigateUrl("checkboxurl");
		test.log(LogStatus.INFO, "Navigated to url :-" + eprop.getProperty("checkboxurl"));
		
	  }
	  
	  
	@Test(groups = {"regression","smoke"})
	public void checkboxTest()
	{
		List<WebElement> check=driver.findElements(By.xpath("//td[@class='table5']/input[@type='checkbox']"));
		for(int i=0;i<check.size();i++)
		{
			System.out.println(check.get(i).getAttribute("value")+"--"+check.get(i).getAttribute("checked"));
			//System.out.println(check.get(i).isSelected());
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
