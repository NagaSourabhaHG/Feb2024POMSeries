package com.qa.opencart.utils;

public class StringUtil {
	
	public  static String generateEmailID()
	{
		String emailID= "userauto"+System.currentTimeMillis()+"@opencart.com";
		
		System.out.println("Email Id of the user is : "+emailID);
		return emailID;
	}
	

}
