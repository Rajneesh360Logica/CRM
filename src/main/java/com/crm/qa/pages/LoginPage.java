package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	// Object Repository : OR
	@FindBy(xpath="//input[@name='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[text()='Something went wrong...']")
	WebElement validationMessage;
	
	
	// Initilization the page Objects
    public LoginPage()
    {
   	 PageFactory.initElements(driver, this);
    }
	
	// Actions : Methods/ Features
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public HomePage login(String un, String pass)
	{
		username.sendKeys(un);
		password.sendKeys(pass);
		loginButton.click();
		
		return new HomePage();
	}
	
	public String invalidLogin()
	{
		username.sendKeys("Test");
		password.sendKeys("Test");
		loginButton.click();
		return validationMessage.getText();
	}
	

}
