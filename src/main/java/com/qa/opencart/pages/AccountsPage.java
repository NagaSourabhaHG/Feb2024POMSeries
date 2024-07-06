package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public String getAccPageTitle() {

		String title = eleutil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_TIME);
		System.out.println("Accounts Page title is : " + title);
		return title;
	}

	public String getAccPagePageURL() {

		String url = eleutil.waitForURLToBe(AppConstants.ACCOUNT_PAGE_FRACTION_URL, TimeUtil.DEFAULT_SHORT_TIME);

		System.out.println("Current url of the page :" + url);
		return url;
	}

	public boolean isLogoutLinkExist() {

		return eleutil.isElementDisplayed(logoutLink);
	}

	public List<String> getAccPageHeaders() {

		List<WebElement> headersList = eleutil.waitForPresenceOfElementsLocated(headers, TimeUtil.DEFAULT_SHORT_TIME);
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValueList.add(text);

		}
		return headersValueList;
	}

	public boolean isSearchExist() {

		return eleutil.doIsDisplayed(search);
	}

	public SearchResultsPage doSearch(String searchKey) {

		System.out.println("Searching : " + searchKey);
		if (isSearchExist()) {

			eleutil.doSendKeys(search, searchKey);
			eleutil.doClick(searchIcon);
			return new SearchResultsPage(driver);

		} else {
			System.out.println("Search field is not displayed on the page");
			return null;
		}

	}

}
