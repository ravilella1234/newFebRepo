package com.project.CarrierIT;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BaseTest
{
	public static ExtentReports report;
	
	public static ExtentReports getInstance()
	{
		if (report==null) 
		{
			report = new ExtentReports(projectPath + "//HTMLReports//" + filePath + ".html");
			report.loadConfig(new File(projectPath + "//Extent-config.xml"));
			report.addSystemInfo("Environment", envprop.getProperty("env")).addSystemInfo("Selenium Binding","3.141.59");
		}
		return report;
	}
	
}
