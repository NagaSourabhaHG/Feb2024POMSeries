package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage  {

	private WebDriver driver;
	private ElementUtil eleutil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil=new ElementUtil(driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	/**
	 * This functions creates a user 
	 * If parameter of method and By locators have names refer By locator parameter with this 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephone
	 * @param password
	 * @param subscribe
	 * @return 
	 */
	
	
	public boolean createsUser(String firstName,String lastName,String email,String telephone,String password,String confPwd,String subscribe) throws Exception{
		
		eleutil.doSendKeys(this.firstName, firstName, TimeUtil.DEFAULT_MEDIUM_TIME);
		Thread.sleep(1000);

		eleutil.doSendKeys(this.lastName, lastName);
		Thread.sleep(1000);

		eleutil.doSendKeys(this.email, email);
		Thread.sleep(1000);

		eleutil.doSendKeys(this.telephone, telephone);
		Thread.sleep(1000);

		eleutil.doSendKeys(this.password, password);
		Thread.sleep(1000);
		eleutil.doSendKeys(this.confirmpassword, confPwd);

		
		if(subscribe.equalsIgnoreCase("yes"))
			eleutil.doClick(subscribeYes);
		else
			eleutil.doClick(subscribeNo);
		
		eleutil.doClick(agreeCheckBox);
		Thread.sleep(1000);

		eleutil.doClick(continueButton);
		Thread.sleep(1000);

		String successMesg = eleutil.waitForElementVisible(successMessg, TimeUtil.DEFAULT_MEDIUM_TIME).getText();

		System.out.println(successMesg);		
				
		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		} else {
			return false;
		}
	




		
		
	
	}

}
