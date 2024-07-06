package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productinfopage;
	protected ShoppingCartPage shoppingcartpage;
	protected SoftAssert softAssert;

	@Step("set up for the test,intializing the browser:{0}")
	@Parameters({ "browser" })
	@BeforeTest

	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		driver = df.initDriver(prop);

		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@Step("Closing the browser:{0}")

	@AfterTest
	public void tearDown()

	{
		driver.quit();
	}
}
