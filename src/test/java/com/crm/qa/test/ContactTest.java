package com.crm.qa.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.Contact;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Helper;

public class ContactTest extends TestBase{
	
	Contact contactPage;
	LoginPage loginPage;
	HomePage homePage;
	
	public ContactTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initilization();
		loginPage=new LoginPage();
	    contactPage=new Contact();
	    homePage=new HomePage();
	    loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void validateSelectContact() throws InterruptedException
	{
		test=extent.createTest("validateSelectContact");
		homePage.clickMenuItem(prop.getProperty("menuContacts"));
		contactPage.selectContact("vibha@tesing.com");
	}
	
	@Test 
	public void validateDeleteContact()
	{
		test=extent.createTest("validateDeleteContact");
		homePage.clickMenuItem(prop.getProperty("menuContacts"));
		contactPage.deleteContact("rajneesh@Testing.com");
	}
	
	@DataProvider
	public Object [][] getCRMTestData()
	{
		Object data[][]=Helper.getTestData(prop.getProperty("sheetName"));
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateContact(String fName, String lName, String cName) throws InterruptedException
	{
		test=extent.createTest("validateCreateContact");
		homePage.clickMenuItem(prop.getProperty("menuContacts"));
		//contactPage.createNewContact("Rajneesh", "Kumar", "Ebay");
		contactPage.createNewContact(fName, lName, cName);
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
