package com.crm.qa.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.Contact;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactTest extends BaseTest{

	

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
		homePage.clickMenuItem(prop.getProperty("menuContacts"));
		contactPage.selectContact("vibha@tesing.com");
	}
	
	@Test 
	public void validateDeleteContact()
	{
		homePage.clickMenuItem(prop.getProperty("menuContacts"));
		contactPage.deleteContact("rajneesh@Testing.com");
	}
	
	@DataProvider
	public Object [][] getCRMTestData()
	{
		Object data[][]=TestUtil.getTestData(prop.getProperty("sheetName"));
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateContact(String fName, String lName, String cName) throws InterruptedException
	{
		homePage.clickMenuItem(prop.getProperty("menuContacts"));
		//contactPage.createNewContact("Rajneesh", "Kumar", "Ebay");
		contactPage.createNewContact(fName, lName, cName);
	}
	
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
}
