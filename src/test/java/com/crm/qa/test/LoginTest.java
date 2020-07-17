package com.crm.qa.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.beust.jcommander.Parameter;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Helper;

public class LoginTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initilization();
	    loginPage=new LoginPage();
	    //homePage=new HomePage();
	}
	
	@Test(priority=1)
	public void validatePageTitle()
	{
		test=extent.createTest("validatePageTitle");
		String title=loginPage.getPageTitle();
		System.out.println("Page tile is: "+ title);
		Assert.assertEquals(title, "Cogmento CRM1");
	}
	
	@Test(priority=2)
	public void validateLogin()
	{
		test=extent.createTest("validateLogin");
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String user=homePage.getloggedUser();
		System.out.println("User Name is: "+user);
		Assert.assertEquals(user, "vibha gupta");
	}
	
	@Test(priority=2)
	public void validateInvalidLogin()
	{
		test=extent.createTest("validateInvalidLogin");
		String mgs=loginPage.invalidLogin();
		Assert.assertEquals(mgs, "Something went wrong...", "Validation message is not correct");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "Test Case Failed is: "+result.getName());
			String screenshotPath=Helper.takeScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		}
		
		if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, "Test Case Skip is: "+result.getName());
		}
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "Test Case Passed is: "+result.getName());
		}
		
		driver.quit();
	}
	
}
