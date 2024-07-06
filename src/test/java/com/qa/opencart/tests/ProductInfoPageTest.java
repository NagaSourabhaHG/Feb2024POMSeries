package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" },
				{ "canon", "Canon EOS 5D" }

		};
	}

	@Test(dataProvider = "getSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productinfopage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productinfopage.productHeader(), productName, AppError.HEADER_NOT_FOUND);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "imac", "iMac", 3 },
				{ "samsung", "Samsung SyncMaster 941BW", 1 }, { "samsung", "Samsung Galaxy Tab 10.1", 7 },
				{ "canon", "Canon EOS 5D", 3 }

		};
	}

	@Test(dataProvider = "getProductData")
	public void productImageCountTest(String searchKey, String productName, int ImageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productinfopage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productinfopage.getProductImagesCount(), ImageCount, AppError.IMAGES_COUNT_MISMATCHED);
	}

	@Test
	public void addProductToCart() {
		searchResultsPage = accPage.doSearch("macbook");
		productinfopage = searchResultsPage.selectProduct("MacBook Pro");
		shoppingcartpage = productinfopage.addProductToCart("2");
	}

	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productinfopage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productinfopage.getProductInfoMap();
		System.out.println("=====Product Information ====");
		System.out.println(productInfoMap);
		Assert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		
		//test with multiple assertion(soft)
		//single assertion-hard assertion
		//multiple assertion-soft assertion
		//Assert and verify

		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");
		softAssert.assertAll();//Failure details 

	}

}
