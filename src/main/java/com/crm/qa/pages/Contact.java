package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class Contact extends BaseTest {
	
	
	
	@FindBy(xpath="//div[text()='Contacts']")
	WebElement contactHeaderText;
	
	@FindBy(xpath="//div[text()='Contacts']")
	WebElement contactCheckbox;
	
	@FindBy(xpath="//button[@class='ui linkedin button' and text()='New']")
	WebElement newButton;
	
	@FindBy(name="first_name")
	WebElement firstNameTextBox;
	
	@FindBy(name="last_name")
	WebElement lastNameTextBox;

	@FindBy(xpath="//div[@name='company']//input[@class='search']")
	WebElement companyTextBox;

	@FindBy(xpath="//button[@class='ui linkedin button' and text()='Save']")
	WebElement saveButton;
	
	// Initilization the page Objects
    public Contact()
    {
   	 PageFactory.initElements(driver, this);
    }
    
    // To get the header text of contact page
    public String getContactHeader()
    {
    	return contactHeaderText.getText();
    }

    // To select the contact based on email Id
    public void selectContact(String emailID) throws InterruptedException
    {
    	driver.findElement(By.xpath("//td[text()='"+emailID+"']")).click();
    }
    
    // To delete the contact based on email Id
    public void deleteContact(String emailID)
    {
     WebElement delectContactButton=driver.findElement(By.xpath("//td[text()='"+emailID+"']//ancestor::tr//td[@class='right aligned collapsing options-buttons-container']//div//button"));
     delectContactButton.click();
     driver.findElement(By.xpath("//button[@class='ui button']")).click();
    }
    
    // To create a new contact
    public void createNewContact(String firstName, String lastName, String companyName) throws InterruptedException
    {
    	Thread.sleep(6000);
    	newButton.click();
    	Thread.sleep(6000);
    	firstNameTextBox.sendKeys(firstName);
    	lastNameTextBox.sendKeys(lastName);
    	companyTextBox.sendKeys(companyName);
    	saveButton.click();
    }

}
