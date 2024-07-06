package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By searchResult = By.cssSelector(".product-thumb");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public int searchResultCount() {
		List<WebElement> resultsList = eleutil.waitForVisibilityOfElementsLocated(searchResult,
				TimeUtil.DEFAULT_SHORT_TIME);
		int resultCount = resultsList.size();
		System.out.println("product result count====" + resultCount);
		return resultCount;

	}

	public ProductInfoPage selectProduct(String productName) {
		eleutil.doClick(By.linkText(productName), TimeUtil.DEFAULT_SHORT_TIME);

		return new ProductInfoPage(driver);
	}

}
