package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.util.Helper;

public class TestBase {
	
	public static Properties prop;
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public TestBase()
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
	   // htmlReporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Report/FreeCRM_"+Helper.getCurrentDateTime()+".html"));
	    htmlReporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Report/FreeCRM.html"));
		htmlReporter.config().setDocumentTitle("Automtion Report");
		htmlReporter.config().setReportName("Funcational Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setAutoCreateRelativePathMedia(false);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", Helper.getMachineName());
		extent.setSystemInfo("OS", Helper.getOpetatingSystem());
		extent.setSystemInfo("User", Helper.getUsername());
		extent.setSystemInfo("Browser", prop.getProperty("browser"));
	}
	
	@AfterSuite
	public void endreport()
	{
		extent.flush();	
	}

	public void initilization()
	{
		String userDir=Helper.getCurrentDir();
		String browserName=prop.getProperty("browser");
		String url=prop.getProperty("url");
		if(browserName.equalsIgnoreCase("chrome"))
		{
        System.setProperty("webdriver.chrome.driver", userDir+"/driver/chromedriver.exe");
	    driver=new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", userDir+"/driver/geckodriver.exe");
		    driver=new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", userDir+"/driver/IEDriverServer.exe");
		    driver=new InternetExplorerDriver();
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Helper.PAGE_LOD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Helper.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

}
