package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By productHeader = By.cssSelector("#content h1");
	private By productImagesCount = By.cssSelector("div#content a.thumbnail");
	private By quanity = By.cssSelector("#input-quantity");
	private By addToCartCTA = By.id("button-cart");
	private By successMsg = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By productMetdata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}

	public String productHeader() {
		String header = eleutil.doGetText(productHeader);

		System.out.println("product header ===" + header);

		return header;

	}

	public int getProductImagesCount() {
		int imagesCount = eleutil.waitForVisibilityOfElementsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_TIME)
				.size();
		System.out.println("images count===" + imagesCount);
		return imagesCount;
	}

	public ShoppingCartPage addProductToCart(String qty) {
		eleutil.doSendKeys(quanity, qty);
		eleutil.doClick(addToCartCTA, TimeUtil.DEFAULT_SHORT_TIME);

		eleutil.doClick(successMsg, TimeUtil.DEFAULT_SHORT_TIME);
		return new ShoppingCartPage(driver);

	}

	public Map<String, String> getProductInfoMap() {
		//productMap = new HashMap<String, String>();
		//productMap=new LinkedHashMap<String,String>();
		productMap=new TreeMap<String,String>();

		
		productMap.put("productname", productHeader());
		productMap.put("productImagecount", String.valueOf(getProductImagesCount()));
		getMetdata();
		getProductPriceData();
		return productMap;

	}

	/*
	 * Brand: Apple Product Code: Product 18 Reward Points: 800 Availability: In
	 * Stock
	 * 
	 */

	private void getMetdata() {
		List<WebElement> metaList = eleutil.getElements(productMetdata);
		for (WebElement e : metaList) {
			String metadata = e.getText();
			String meta[] = metadata.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);

		}
	}

	/*
	 * $2,000.00 Ex Tax: $2,000.00
	 * 
	 */

	private void getProductPriceData() {

		List<WebElement> priceList = eleutil.getElements(productPriceData);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);

	}

}
