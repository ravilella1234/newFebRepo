package com.project.CarrierIT;

import com.relevantcodes.extentreports.LogStatus;

public class TC_005 extends BaseTest
{

	
	
	public static void main(String[] args) throws Exception
	{
		init();
		test=r.startTest("TC_005");
		test.log(LogStatus.INFO, "Initializing the Properties Files....");
		
		launch("chromebrowser");
		test.log(LogStatus.INFO, "Opened the browser :-" + p.getProperty("chromebrowser"));
		
		navigateUrl("amazonurl");
		test.log(LogStatus.INFO, "Navigated to url :-" + eprop.getProperty("amazonurl"));
		
			
		selectOption("amazonsearchdropbox_id","amazondropvalue");
		test.log(LogStatus.INFO, "Seleted the option :- "+ or.getProperty("amazondropvalue") + " by using the locator :- " + or.getProperty("amazonsearchdropbox_id") );
		
		typeValue("amazonsearchdropbox_name","amazontext");
		test.log(LogStatus.INFO, "Entered the text :- " + or.getProperty("amazontext") + " By using the locator :- " +  or.getProperty("amazonsearchdropbox_name"));
		
		clickElement("amazonsearchbutton_xpath");
		test.log(LogStatus.INFO, "Clicked on button by using the locator  :-" + or.getProperty("amazonsearchbutton_xpath"));
		
		
		r.endTest(test);
		r.flush();

	}

	
}
