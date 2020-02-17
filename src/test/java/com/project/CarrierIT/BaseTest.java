package com.project.CarrierIT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest
{
	public static WebDriver driver;
	public static String projectPath=System.getProperty("user.dir");
	public static FileInputStream fis;
	public static Properties p;
	public static Properties envprop;
	public static Properties eprop;
	public static Properties or;
	public static String filePath;
	public static ExtentReports r;
	public static ExtentTest test;
	
	
	static
	{
		Date dt=new Date();
		filePath= dt.toString().replace(':', '_').replace(' ', '_');
	}
	
	public static void init() throws Exception
	{
		fis=new FileInputStream(projectPath+"//data.properties");
		p=new Properties();
		p.load(fis);
		
		fis=new FileInputStream(projectPath+"//environment.properties");
		envprop=new Properties();
		envprop.load(fis);
		String e1 = envprop.getProperty("env");
		System.out.println(e1);
		
		fis=new FileInputStream(projectPath+"//"+e1+".properties");
		eprop=new Properties();
		eprop.load(fis);
		String e2 = eprop.getProperty("amazonurl");
		System.out.println(e2);
		
		fis=new FileInputStream(projectPath+"//or.properties");
		or=new Properties();
		or.load(fis);
		
		PropertyConfigurator.configure(projectPath+"//log4jconfig.properties");
		
		r = ExtentManager.getInstance();
	}
	
	public static void launch(String browser)
	{
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Desktop\\drive\\chromedriver.exe");
			ChromeOptions option=new ChromeOptions();
			
			option.addArguments("user-data-dir=C:\\Users\\DELL\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 3");
			option.addArguments("--disable-notifications");
			
			driver=new ChromeDriver(option);
		}
		else if(browser.equals("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\DELL\\Desktop\\drive\\geckodriver.exe");
			ProfilesIni p=new ProfilesIni();
			FirefoxProfile profile = p.getProfile("ffprofile");
			
			profile.setPreference("dom.webnotifications.enabled", false);
			
			FirefoxOptions option=new FirefoxOptions();
			option.setProfile(profile);
			
			driver=new FirefoxDriver(option);
		}
	}
	
	public static void navigateUrl(String url)
	{
		//driver.get(eprop.getProperty(url));
		driver.navigate().to(eprop.getProperty(url));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void clickElement(String locatorKey) {
		getElement(locatorKey).click();
		//driver.findElement(By.xpath(or.getProperty(locatorKey))).click();
	}

	private static WebElement getElement(String locatorKey) 
	{
		WebElement element=null;
		
		if(locatorKey.endsWith("_id")) {
			element=driver.findElement(By.id(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_name")) {
			element=driver.findElement(By.name(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_classname")) {
			element=driver.findElement(By.className(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_xpath")) {
			element=driver.findElement(By.xpath(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_css")) {
			element=driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_linktext")) {
			element=driver.findElement(By.linkText(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_partiallinktext")) {
			element=driver.findElement(By.partialLinkText(or.getProperty(locatorKey)));
		}
		
		return element;
	}

	public static void typeValue(String locatorKey, String value) {
		getElement(locatorKey).sendKeys(or.getProperty(value));
		//driver.findElement(By.name(or.getProperty(locatorKey))).sendKeys(or.getProperty(value));
	}

	public static void selectOption(String locatorKey, String item) {
		getElement(locatorKey).sendKeys(or.getProperty(item));
		//driver.findElement(By.id(or.getProperty(locatorKey))).sendKeys(or.getProperty(item));
	}
	
	//******************** Verifications  ********************
	
	public static boolean verifyLink(String expectedLink) {
		String actualLink = driver.findElement(By.linkText("AmazonBasics")).getAttribute("innerHTML");
		if(actualLink.contains(expectedLink))
			  return true;
		  else
			  return false;
	}
	
	
	//  *********************  Reportings  *********************
	
	public static void reportSuccess(String successMsg) {
		test.log(LogStatus.PASS, successMsg);
	}

	public static void reportFailure(String failureMsg) {
		test.log(LogStatus.FAIL, failureMsg);
		takeScreenshot();
	}

	public static void takeScreenshot() 
	{
		Date dt=new Date();
		//screenshotFileName = dt.toString().replace(":", "_").replace(" ", "_")+".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileHandler.copy(scrFile, new File(projectPath+"//failure//"+filePath+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//put screen shot file in extent reports
		test.log(LogStatus.INFO, "Screenshot --> "+ test.addScreenCapture(projectPath+"//failure//"+filePath+".png"));
	}
	
	
	public static void waitforElement(WebElement element,int timeoutInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void selectItem(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}


}
