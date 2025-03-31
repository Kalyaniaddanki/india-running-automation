package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.pages.BaseClass;
import com.qa.pages.BombayRunningPage;
import com.qa.pages.EventDetailsPage;
import com.qa.pages.EventInformationPage;
import com.qa.pages.SignInPage;
import com.qa.pages.TicketPage;

public class EventIsDisplayedInINdiaRunningWebsite_Tc_003 extends BaseClass { 
	public static String emailAddress = "kalyani18402@gmail.com";
	public static String otp1 = "1";
	public static String otp2 = "2";
	public static String otp3 = "3";
	public static String opt4 = "4";
	
	
	@Test
	public void eventIsdisplayedInWebsite() throws InterruptedException{
		SignInPage signinPage = new SignInPage(driver);
		EventInformationPage infomationPage = new EventInformationPage(driver);
		EventDetailsPage deatilsPage = new EventDetailsPage(driver);
		TicketPage ticket = new TicketPage(driver);
		BombayRunningPage runningPage = new BombayRunningPage(driver);
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
		infomationPage.enterEventInformation();
		test.info("User enters the event information deatils");
		infomationPage.enterStartDateAndEndDate();
		test.info("User enters the start and end dates of the event");
		infomationPage.ensterLocationAndAdreess();
		test.info("User enters the event's location to be conducted in");
		deatilsPage.enterAboutRaceDetails();
		test.info("User enters about the race details");
		ticket.clickRunningAndNameOfCategory();
		test.info("User enters name of the category");
		ticket.enterTicketPriceAndAge();
		test.info("User enters  the ticket price and other deatils");
		ticket.proceedToCreateForm();
		test.info("User will create the form");
		ticket.validateEventIscreatedSuccessfully();
		test.pass("user created the event scuccessfully");
		runningPage.enterTheEvent();
		runningPage.validateTheEventIsPresent();
	}

}
