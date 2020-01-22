package com.fadx.maven.fashion.Base;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import com.fadx.maven.fasion.Utils.PropertyReader;
import com.fadx.maven.fasion.Utils.SQLConnector;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;




public class BaseClass<LoginfadxPageMethods>
{
	protected static WebDriver driver;
	protected Genric genric;
	protected String filePath = PropertyReader
			.readDataProperty("ExtentPath")+ "Extent.html";
	protected  ExtentReports  extent = ExtentManager.getReporter(filePath);
	protected ExtentTest test;
	protected String DownloadPath = PropertyReader
			.readDataProperty("DownloadPath");
		protected String methodname;

	protected SQLConnector sqlconnection;

	
	public WebDriver getDriver() {
		return driver;
	}
	
	//@BeforeSuite
	protected void beforeSuite(){
		File file = new File(PropertyReader
				.readDataProperty("ExtentPath"));
		try {
			FileUtils.cleanDirectory(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@AfterSuite
    protected void afterSuite() {    	
        extent.close();
    }
	
	
	
	
}
