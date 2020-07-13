package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class Calendar extends BaseTest{
	
	@FindBy(xpath="//div[text()='Calendar']")
	WebElement calendarHeaderText;
	
	// Initilization the page Objects
    public Calendar()
    {
   	 PageFactory.initElements(driver, this);
    }
    
    public String getCalendarHeader()
    {
    	return calendarHeaderText.getText();
    }

}
