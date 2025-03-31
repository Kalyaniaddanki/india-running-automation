package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.pages.BaseClass;
import com.qa.pages.EventInformationPage;
import com.qa.pages.SignInPage;

public class createEventWithmissingRequiredFields_Tc_006 extends BaseClass {
	
	public static String emailAddress = "kalyani18402@gmail.com";
	public static String otp1 = "1";
	public static String otp2 = "2";
	public static String otp3 = "3";
	public static String opt4 = "4";
	
	@Test
	public void eventWithMissingFields() throws InterruptedException {
		
		SignInPage signinPage = new SignInPage(driver);
		EventInformationPage infomationPage = new EventInformationPage(driver);
		// user enters the email address to login into india running website
		signinPage.enterEmailAddress(emailAddress);
		test.info("User enters the email address");
		signinPage.clickGetOtpButton();
		signinPage.userEntersOtp(otp1, otp2, otp3, opt4);
		signinPage.clickVerifyOtp();
		// validate user is login successfully into the applicaton 
		infomationPage.validateUserIsLoginedSuccessfully();
		test.pass("User is logined into the application successfully");
		infomationPage.clickonCreateEvent();
		test.info("User creates the event");
		infomationPage.enterInvalidInformation();
		test.info("User enters the event information deatils without Event name");
		infomationPage.enterStartDateAndEndDate();
		test.info("User enters the start and end dates of the event");
		infomationPage.clickSaveAndProceedWithouEnteringEventName();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
