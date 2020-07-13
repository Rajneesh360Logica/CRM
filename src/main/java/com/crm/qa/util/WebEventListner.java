package com.crm.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.crm.qa.base.BaseTest;

public abstract class WebEventListner extends BaseTest implements WebDriverEventListener{
	
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		System.out.println("In afterChangeValueOf");
		}

		@Override
		public void afterClickOn(WebElement element, WebDriver arg1) {
		// TODO Auto-generated method stub

			System.out.println("Clicked on: "+ element.toString());
		}

		@Override
		public void afterFindBy(By arg0, WebElement element, WebDriver arg2) {
		// TODO Auto-generated method stub

			//System.out.println(element.toString() +"Element is being find");
		}

		@Override
		public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		}

		public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeClickOn(WebElement element, WebDriver arg1) {
		// TODO Auto-generated method stub

			System.out.println("Trying to click on "+ element.toString());
		}

		@Override
		public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		}

		@Override
		public void onException(Throwable error, WebDriver driver) {
		// TODO Auto-generated method stub
			System.out.println("Exception: "+error);
			TestUtil.takeScreenshot("DefaultName");
		}

		@Override
		public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

		}

		@Override
		public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

		}

		@Override
		public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		}

		@Override
		public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
			// TODO Auto-generated method stub
			System.out.println("Screenshot captured successfully.");
		}

		@Override
		public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterSwitchToWindow(String arg0, WebDriver arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
			// TODO Auto-generated method stub
			System.out.println("Trying to take Screenshot.");
		}

		@Override
		public void beforeGetText(WebElement arg0, WebDriver arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
			// TODO Auto-generated method stub
			
		} 


}
