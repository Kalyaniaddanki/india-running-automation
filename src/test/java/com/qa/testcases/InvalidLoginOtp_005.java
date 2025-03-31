package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.pages.BaseClass;
import com.qa.pages.SignInPage;

public class InvalidLoginOtp_005  extends BaseClass {
	public static String emailAddress = "kalyani18402@gmail.com";
	public static String otp1 = "1";
	public static String otp2 = "2";
	public static String otp3 = "0";
	public static String opt4 = "0";
	
	
	@Test 
	public void loginWithWrongOtp() {
		
		SignInPage signinPage = new SignInPage(driver);
		// user enters the email address to login into india running website
		signinPage.enterEmailAddress(emailAddress);
		test.info("User enters the email address");
		signinPage.clickGetOtpButton();
		signinPage.userEntersOtp(otp1, otp2, otp3, opt4);
		signinPage.clickVerifyOtp();
		signinPage.validateIncorrectOtp();
		
		
		
	}
	
	
	
	

}
