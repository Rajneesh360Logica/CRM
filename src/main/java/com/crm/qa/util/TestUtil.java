package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.BaseTest;

public class TestUtil extends BaseTest{
	
	public static long PAGE_LOD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=30;
	public static String filePath=getCurrentDir()+"\\src\\main\\java\\com\\crm\\qa\\testdata\\TestData.xlsx";
	
	// To get the data in 2D array
	public static Object [][] getTestData(String sheetName)
	{
		Object data[][]=null;
		
		File src=new File(filePath);

		try 
		{
			FileInputStream ip=new FileInputStream(src);
			XSSFWorkbook wb=new XSSFWorkbook(ip);
			XSSFSheet sheet=wb.getSheet(sheetName);
			
			
			System.out.println("Sheet Name: "+sheet.getSheetName());
			//int count=sheet.getLastRowNum();
			
			
			 data =new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i<sheet.getLastRowNum(); i++)
			{
				for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++)
				{
				 data[i][k]=sheet.getRow(1+i).getCell(k).getStringCellValue();
			
				}
			}
			
			//wb.close();
		}
		catch (Exception e)
		{
          System.out.println(e.getMessage());
		}
		return data;
	}
	
	// To take the screenshot
	public static String takeScreenshot(String screenshotName)
	{
		String destination=null;
		String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		try {
	       //  Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			
			// Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			
			//Move image file to new destination
			String currentDir=System.getProperty("user.dir");
			System.out.println(currentDir);
			//File DestFile=new File(currentDir+"\\Images\\Test.png");
			
			//File DestFile=new File(currentDir+"\\Screenshots\\"+System.currentTimeMillis()+".png");
			// Copy file to Desired Location
			
		    destination=currentDir+"\\Report\\"+screenshotName+dateName+".png";
			File finalDestnation=new File(destination);
		
				FileUtils.copyFile(SrcFile, finalDestnation);
				
			} 
		catch (Exception e) 
		   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return destination;
	}

	// To get current directory 
	public static String getCurrentDir()
	{
		return System.getProperty("user.dir");
	}
	
}
