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
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public BaseTest()
	{
		try
		{
			prop=new Properties();
			FileInputStream ip=new FileInputStream("C:\\Users\\rajneeshk\\Desktop\\ProjectsDirectory\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		    prop.load(ip);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//@BeforeSuite
	public void setUpSuite()
	{
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Report/FreeCRM.html"));
		htmlReporter.config().setDocumentTitle("Automtion Report");
		htmlReporter.config().setReportName("Funcation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "Localhost");
		extent.setSystemInfo("OS", "Windo ws 10");
		extent.setSystemInfo("User", "Rajneesh");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	
	public void initilization()
	{
		setUpSuite();
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
