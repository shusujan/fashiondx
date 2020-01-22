package com.fadx.maven.fashion.PageMethods;

import org.openqa.selenium.WebDriver;

import com.fadx.maven.fashion.Base.Genric;
import com.fadx.maven.fashion.PageObjects.ShopperListPageObjects;

public class ShopperListPageMethods {
	public WebDriver driver;
	Genric genric;
	public ShopperListPageMethods(WebDriver driver, Genric genric)
	{
		this.driver=driver;
		this.genric=genric;
	}
	public void AddShoppers(){			
		genric.waitForLoading();
		genric.element(ShopperListPageObjects.Add_new_shoppers).click();
		
				}
}
