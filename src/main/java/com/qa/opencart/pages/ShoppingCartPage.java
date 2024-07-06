package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	public ShoppingCartPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
		
	}
}
