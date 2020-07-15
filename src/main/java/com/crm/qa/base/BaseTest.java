package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListner;

public class BaseTest {
	
	public static Properties prop;
	public static WebDriver driver;
	public EventFiringWebDriver e_driver;
	public WebEventListner webEventListner;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public BaseTest()
	{
		try
		{
			prop=new Properties();
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		    prop.load(ip);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@BeforeSuite
	public void setExtent()
	{
	    htmlReporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Report/FreeCRM.html"));
		htmlReporter.config().setDocumentTitle("Automtion Report");
		htmlReporter.config().setReportName("Funcational Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setAutoCreateRelativePathMedia(false);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "360NDCLP182");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("User", "Rajneesh");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	@AfterSuite
	public void endreport()
	{
		extent.flush();	
	}

	
	
	public void initilization()
	{
		String userDir=TestUtil.getCurrentDir();
		String browserName=prop.getProperty("browser");
		String url=prop.getProperty("url");
		if(browserName.equals("chrome"))
		{
        System.setProperty("webdriver.chrome.driver", userDir+"/driver/chromedriver.exe");
	    driver=new ChromeDriver();
		}
		
		else if(browserName.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver", userDir+"/driver/geckodriver.exe");
		    driver=new FirefoxDriver();
		}
		
		else if(browserName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", userDir+"/driver/IEDriverServer.exe");
		    driver=new InternetExplorerDriver();
		}
		
		/*e_driver=new EventFiringWebDriver(driver);
		webEventListner=new WebEventListner();
		e_driver.register(webEventListner);
		driver=e_driver;*/
		
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

}
