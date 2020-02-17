package com.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	@FindBy(xpath="//a[@class='login']")public WebElement signinLink;
	@FindBy(id="email")public WebElement customerEmail;
	@FindBy(id="passwd")public WebElement customerPassword;
	@FindBy(xpath="//p[@class='submit']//span[1]")public WebElement signinButton;
	@FindBy(xpath="//li[contains(text(),'Authentication failed.')]")public WebElement loginError;
	
	
	  public LoginPage(WebDriver driver) 
	  { 
		  PageFactory.initElements(driver, this);
	  }
	 

	public void customerLogin()
	{
		signinLink.click();
		customerEmail.sendKeys("qatest845747@gmail.com");
		customerPassword.sendKeys("password");
		signinButton.click();
	}
	
	
	public String getLoginError()
	{
		return loginError.getText();
	}

}
