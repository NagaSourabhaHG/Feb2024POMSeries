package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// Page Objects:By Locators
	private By emailID = By.id("input-email");
	private By passwordID = By.id("input-password");
	private By LoginBTN = By.xpath("//input[@class='btn btn-primary']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// public constructor of the page

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3.public page methods/actions

	@Step("Fetching the title")

	public String getLoginPageTitle() {

		String title = eleutil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_TIME);
		System.out.println("Login Page title is : " + title);
		return title;
	}

	@Step("Fetching the URL")
	public String getLoginPageURL() {

		String url = eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_SHORT_TIME);

		System.out.println("Current url of the page :" + url);
		return url;
	}

	@Step("Verfying forgot password link")

	public boolean checkForgotPasswordLink() {

		return eleutil.doIsDisplayed(forgotPwdLink);
	}

	@Step("Login to application with username:{0} and password:{1}")

	public AccountsPage doLogin(String username, String password) {

		System.out.println("User credentials are  " + username + password);

		eleutil.doSendKeys(emailID, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleutil.doSendKeys(passwordID, password);
		eleutil.doClick(LoginBTN);

		return new AccountsPage(driver);

	}
	@Step("Navigates to regsitation page")

	public RegistrationPage navigateToRegisterPage() {
		eleutil.doClick(registerLink, TimeUtil.DEFAULT_SHORT_TIME);
		return new RegistrationPage(driver);
	}
}
