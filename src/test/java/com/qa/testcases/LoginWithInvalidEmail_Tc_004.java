package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.pages.BaseClass;
import com.qa.pages.SignInPage;

public class LoginWithInvalidEmail_Tc_004 extends BaseClass {
	public static String emailAddress = "Kla.com";
	
	@Test
	public void loginWithInvalidEmail() throws InterruptedException {
		SignInPage signinPage = new SignInPage(driver);
		signinPage.enterEmailAddress(emailAddress);
		test.info("User enters the email address");
		signinPage.clickGetOtpButton();
		signinPage.validateuserShouldNotEnterOtp();
		test.info("User entered invalid email");
		
	}
	
	
	
	

}
