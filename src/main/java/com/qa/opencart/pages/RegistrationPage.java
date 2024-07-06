package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil=new ElementUtil(driver);
	}

}
