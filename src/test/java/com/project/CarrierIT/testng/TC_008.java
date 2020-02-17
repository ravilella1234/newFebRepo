package com.project.CarrierIT.testng;

import org.testng.annotations.Test;

import com.project.CarrierIT.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;


public class TC_008 extends BaseTest
{
	private static final Logger log=Logger.getLogger(TC_008.class);
  
  @BeforeMethod(groups = {"regression"})
  @Parameters("browser")
  public void startProcess(String bType) throws Exception 
  {
	    init();
		test=r.startTest("TC_008");
		test.log(LogStatus.INFO, "Initializing the Properties Files....");
		log.info("Initializing the Properties Files....");
		Reporter.log("Initializing the Properties Files....");
		
		launch(bType);
		log.info("Opened the browser :-" + bType);
		test.log(LogStatus.INFO, "Opened the browser :-" + bType);
		Reporter.log("Opened the browser :-" + bType);
		
		navigateUrl("amazonurl");
		log.info("Navigated to url :-" + eprop.getProperty("amazonurl"));
		test.log(LogStatus.INFO, "Navigated to url :-" + eprop.getProperty("amazonurl"));
		Reporter.log("Navigated to url :-" + eprop.getProperty("amazonurl"));
	  
  }
  
  @Test(groups = {"regression"})
  public void amazon() 
  {
	    selectOption("amazonsearchdropbox_id","amazondropvalue");
	    log.info("Seleted the option :- "+ or.getProperty("amazondropvalue") + " by using the locator :- " + or.getProperty("amazonsearchdropbox_id"));
		test.log(LogStatus.INFO, "Seleted the option :- "+ or.getProperty("amazondropvalue") + " by using the locator :- " + or.getProperty("amazonsearchdropbox_id") );
		Reporter.log("Seleted the option :- "+ or.getProperty("amazondropvalue") + " by using the locator :- " + or.getProperty("amazonsearchdropbox_id") );
		
		typeValue("amazonsearchtextbox_id","amazontext");
		log.info("Entered the text :- " + or.getProperty("amazontext") + " By using the locator :- " +  or.getProperty("amazonsearchdropbox_name"));
		test.log(LogStatus.INFO, "Entered the text :- " + or.getProperty("amazontext") + " By using the locator :- " +  or.getProperty("amazonsearchdropbox_name"));
		Reporter.log("Entered the text :- " + or.getProperty("amazontext") + " By using the locator :- " +  or.getProperty("amazonsearchdropbox_name"));
		
		clickElement("amazonsearchbutton_xpath");
		log.info("Clicked on button by using the locator  :-" + or.getProperty("amazonsearchbutton_xpath"));
		test.log(LogStatus.INFO, "Clicked on button by using the locator  :-" + or.getProperty("amazonsearchbutton_xpath"));
		Reporter.log("Clicked on button by using the locator  :-" + or.getProperty("amazonsearchbutton_xpath"));
  }

  @AfterMethod(groups = {"regression"})
  public void endProcess() 
  {
	  driver.quit();
	  
	    r.endTest(test);
		r.flush();
  }

}
