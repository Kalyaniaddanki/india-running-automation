package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.pages.BaseClass;
import com.qa.pages.EventInformationPage;
import com.qa.pages.SignInPage;

public class VerifyUserCanLoginWithValidEmail_Tc_001 extends BaseClass {
	
	public static String emailAddress = "kalyani18402@gmail.com";
	public static String otp1 = "1";
	public static String otp2 = "2";
	public static String otp3 = "3";
	public static String opt4 = "4";
	
	@Test
	public void loginWithValidEmail() throws InterruptedException {
		SignInPage signinPage = new SignInPage(driver);
		EventInformationPage infomationPage = new EventInformationPage(driver);
		signinPage.enterEmailAddress(emailAddress);
		test.info("User enters the email address");
		signinPage.clickGetOtpButton();
		signinPage.userEntersOtp(otp1, otp2, otp3, opt4);
		signinPage.clickVerifyOtp();
		// validate user is login successfully into the applicaton 
		infomationPage.validateUserIsLoginedSuccessfully();
	}
	
	
	
	
	
	
	

}
