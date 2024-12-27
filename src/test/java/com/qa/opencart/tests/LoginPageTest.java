package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Open cart application")
@Story("Design LoginPage")

public class LoginPageTest extends BaseTest {

	@Description("Verify Loginpage title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sourabha")
	@Issue("Login-123")
	@Feature("Login Page Feature")

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);

	}

	@Description("Verify Loginpage URL ")

	@Test(priority = 2)

	@Severity(SeverityLevel.NORMAL)
	@Owner("Sourabha")
	@Feature("Login Page Feature")

	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);

	}

	@Description("Verify ForgetPassword link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Sourabha")
	@Feature("Login Page Feature")

	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPasswordLink(), AppError.ELEMENT_NOT_FOUND);
	}

	@Description("Verify Login")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Sourabha")
	@Feature("Login Page Feature")

	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
}
