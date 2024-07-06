package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {

		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);

	}

	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPagePageURL().contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL));
	}

	@Test
	public void accPageHeadersTest() {
		List<String> accPageHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST, AppError.HEADER_LIST_NOT_FOUND);
	}

	@DataProvider
	public Object[][] getSearchData() {

		return new Object[][] { 
			{ "macbook", 3 },
			{ "imac", 1 } ,
			{"samsung",2},
			{"Airtel",0},

		};
	}

	@Test(dataProvider="getSearchData")
	public void searchTest(String search,int resultcount) {
		searchResultsPage = accPage.doSearch(search);
		Assert.assertEquals(searchResultsPage.searchResultCount(), resultcount, AppError.RESULTS_COUNT_MISMATCHED);

	}

}
