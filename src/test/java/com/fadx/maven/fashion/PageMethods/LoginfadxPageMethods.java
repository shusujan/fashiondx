package com.fadx.maven.fashion.PageMethods;

import org.openqa.selenium.WebDriver;

import com.fadx.maven.fashion.Base.Genric;
import com.fadx.maven.fashion.PageObjects.LoginfadxPageObjects;
import com.fadx.maven.fasion.Utils.PropertyReader;

public class LoginfadxPageMethods {
	public WebDriver driver;
	Genric genric;
	public LoginfadxPageMethods(WebDriver driver, Genric genric){
		this.driver=driver;
		this.genric=genric;
	}
		//Fa DX Login
		public void UserLogin(){			
				genric.waitForLoading();
				genric.element(LoginfadxPageObjects.User_Name).sendKeys(PropertyReader.readDataProperty("Valid_username"));
				genric.element(LoginfadxPageObjects.Pin).sendKeys(PropertyReader.readDataProperty("ValidPassword"));
				genric.element(LoginfadxPageObjects.Click_Go).click();
				genric.waitForLoading();			
			}
}
