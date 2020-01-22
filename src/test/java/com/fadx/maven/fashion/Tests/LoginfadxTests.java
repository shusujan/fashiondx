package com.fadx.maven.fashion.Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fadx.maven.fashion.Base.BaseClass;
import com.fadx.maven.fashion.Base.ExtentManager;
import com.fadx.maven.fashion.Base.Genric;
import com.fadx.maven.fashion.PageObjects.LoginfadxPageObjects;
import com.fadx.maven.fasion.Utils.SQLConnector;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class LoginfadxTests extends BaseClass{
	ExtentReports extent = ExtentManager.getReporter(filePath);

	@SuppressWarnings("unchecked")
	@BeforeMethod	
	public void before() {
		genric = new Genric(driver);
		driver = genric.getDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		genric.launchApplication();
		sqlconnection = new SQLConnector();
}
	// Verify that user is able to Login with Valid credentials
		@Test
		public void TC_01_Login() {
			test = extent.startTest(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
			genric.waitForLoading(); 
			genric.element(LoginfadxPageObjects.Store_Id).sendKeys(("indra_nagar"));
			genric.element(LoginfadxPageObjects.User_Name).sendKeys(("shubha"));
			genric.element(LoginfadxPageObjects.Pin).sendKeys(("1234"));
			genric.element(LoginfadxPageObjects.Click_Go).click();
			
		} 
		@AfterMethod
	    protected void afterMethod(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            test.log(LogStatus.FAIL, result.getThrowable());
	            String screenshot_path= genric.captureScreenShot(driver, result.getName());
	    		String image= test.addScreenCapture(screenshot_path);
	    		test.log(LogStatus.INFO, result.getName(), image);
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
	        } else {
	            test.log(LogStatus.PASS, "Test passed");
	        }

	        extent.endTest(test);        
	       extent.flush();
	        driver.quit();
	    }

}
