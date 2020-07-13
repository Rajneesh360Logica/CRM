package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class HomePage extends BaseTest{
	
	Object object;
	String menuName;
	
	/*@FindBy(xpath="//span[@class='user-display']")
	WebElement userDisplay;*/
	
	@FindBy(className="user-display")
	WebElement userDisplay;
	
	/*@FindAll({@FindBy(xpath="//span[@class='item-text']")})
	List<WebElement> menuList;*/
	

	@FindAll({@FindBy(xpath="//div[@id='main-nav']/a")})
	List<WebElement> menuList;
	
	@FindBy(xpath="//span[text()='Calendar']")
	WebElement calendarMenu;
	
	@FindBy(xpath="//span[text()='Contacts']")
	WebElement contactsMenu;
	
	// Initilization the page Objects
    public HomePage()
    {
   	 PageFactory.initElements(driver, this);
    }
	
    // To return username
	public String getloggedUser()
	{
		return userDisplay.getText();
	}
	
	public void getMenulist()
	{
		//int size=menuList.size();
		for(int i=0; i<menuList.size(); i++)
		{
			System.out.println(menuList.get(i).getText());
		}
	}
	
	public Object clickMenuItem(String menuName)
	{
		for(int i=0; i<menuList.size(); i++)
		{	     
		if(menuList.get(i).getText().equals(menuName))
		{
			menuName=menuList.get(i).getText();
			menuList.get(i).click();
			
			if(menuName.contains("Calendar"))
			{
			object= new Calendar(); 
			}
			else if(menuName.contains("Contacts"))
			{
			object= new Contact(); 
			}
			break;
		}
		}
		 return object;
	}
	
	public Object clickOnCalendar()
	{
		calendarMenu.click();
		
		return new Calendar();
	}
	
	public Contact clickOnContacts()
	{
		contactsMenu.click();
		
		return new Contact();
	}
	

}
