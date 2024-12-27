package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest {
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {{"Sanjana","nest","9876745624","Welcome@123","yes"},
		{"Rahul","automation","9876890678","Welcome@123","no"}
	};
	}
	
	@BeforeClass
	public void regSetup()
	{
		regPage=loginPage.navigateToRegisterPage();
	}
	
	@Test(dataProvider= "getUserData")
	public void createUser(String fname,String lname,String phNo,String Pwd,String Subscribe) throws Exception
	{
		Assert.assertTrue(regPage.createsUser(fname, lname, StringUtil.generateEmailID(), phNo, Pwd,Pwd, Subscribe),AppError.REG_ERROR_MESSAGE);
	}

}
