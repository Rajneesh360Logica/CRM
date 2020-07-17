package com.crm.qa.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.Calendar;
import com.crm.qa.pages.Contact;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Helper;

public class HomeTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	Contact contact;
	Calendar calendar;
	
	public HomeTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initilization();
	    loginPage=new LoginPage();
	    homePage=new HomePage();
	    loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void showMenuList()
	{
		test=extent.createTest("showMenuList");
		homePage.getMenulist();
	}
	
	@Test(priority=2)
	public void verifyManuClick()
	{
		test=extent.createTest("verifyManuClick");
	  homePage.clickMenuItem(prop.getProperty("menuItem"));
	}
	

	@Test(priority=3)
	public void verifyCalenderPage()
	{
		test=extent.createTest("verifyCalenderPage");
		//calendar=(Calendar) homePage.clickOnCalendar();
		calendar=(Calendar) homePage.clickMenuItem(prop.getProperty("menuCalendar"));
		Assert.assertEquals(calendar.getCalendarHeader(), "Calendar", "Incorrect landing page.");
	}
	
	@Test(priority=4)
	public void verifyContactPage()
	{
		test=extent.createTest("verifyContactPage");
		contact=(Contact) homePage.clickMenuItem(prop.getProperty("menuContacts"));
		Assert.assertEquals(contact.getContactHeader(), "Contacts", "Incorrect landing page.");
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
