package com.fadx.maven.fashion.Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fadx.maven.fashion.Base.BaseClass;
import com.fadx.maven.fashion.Base.Genric;
import com.fadx.maven.fashion.PageMethods.LoginfadxPageMethods;
import com.fadx.maven.fashion.PageObjects.LoginfadxPageObjects;
import com.fadx.maven.fashion.PageObjects.ShopperListPageObjects;
import com.relevantcodes.extentreports.LogStatus;

public class ShopperListTests extends BaseClass {
	@BeforeMethod
	public void before() {
		genric = new Genric(driver);
		driver = genric.getDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		genric.launchApplication();
	}
	@Test
	public void AddShoppers() throws AWTException {
		test = extent.startTest(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
		genric.waitForLoading(); 
		genric.element(LoginfadxPageObjects.Store_Id).sendKeys(("indra_nagar"));
		genric.element(LoginfadxPageObjects.User_Name).sendKeys(("shubha"));
		genric.element(LoginfadxPageObjects.Pin).sendKeys(("1234"));
		genric.element(LoginfadxPageObjects.Click_Go).click();
		genric.waitForLoading(); 

		
		genric.element(ShopperListPageObjects.Add_new_shoppers).click();
		genric.waitForLoading(); 

		genric.element(ShopperListPageObjects.Add_shopper_click).click();
		genric.waitForLoading(); 
		genric.element(ShopperListPageObjects.click_guest_added).click();
		genric.waitForLoading(); 

		genric.element(ShopperListPageObjects.click_continue).click();
		genric.waitForLoading(); 
		genric.element(ShopperListPageObjects.scroll).isSelected();
		genric.waitForLoading(); 



		genric.element(ShopperListPageObjects.age_group).isSelected();
		genric.waitForLoading(); 

		genric.element(ShopperListPageObjects.profession_select).isSelected();
		genric.waitForLoading(); 

		genric.element(ShopperListPageObjects.click_continue_1).click();
		genric.element(ShopperListPageObjects.shirt_size).click();
		genric.element(ShopperListPageObjects.click_continue).click();
		genric.element(ShopperListPageObjects.select_face_type).click();
		genric.element(ShopperListPageObjects.click_continue).click();
		genric.element(ShopperListPageObjects.select_hair_color).click();
		genric.element(ShopperListPageObjects.select_eye_color).click();
		genric.element(ShopperListPageObjects.select_skin_color).click();
		genric.element(ShopperListPageObjects.click_continue).click();
		genric.element(ShopperListPageObjects.select_checks_style).click();
		genric.element(ShopperListPageObjects.click_continue).click();

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
 //       driver.quit();
    }

}
