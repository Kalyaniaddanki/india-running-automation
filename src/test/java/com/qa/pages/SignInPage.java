package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class SignInPage extends BaseClass{
	WebDriver driver;
	
	public static By emailAddress = By.id("email");
	public static By getOtp = By.xpath("//*[contains(text(),'Get OTP')]");
	public static By otpBox1 = By.id("otp0");
	public static By otpBox2 = By.id("otp1");
	public static By otpBox3 = By.id("otp2");
	public static By otpBox4 = By.id("otp3");
	public static By verify_otp = By.xpath("//*[contains(text(),'Verify OTP')]");
	public static By invalidEmail = By.xpath("//*[contains(text(),'Invalid Email')]");
	public static By invalidOtp = By.xpath("//*[contains(text(),'Incorrect Otp')]");
	
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterEmailAddress(String email) {
		WebElement userEmailAddress = driver.findElement(emailAddress);
		userEmailAddress.sendKeys(email);
	}
	
	public void clickGetOtpButton() {
		WebElement getOtpButton = driver.findElement(getOtp);
		getOtpButton.click();
	}
	
	public void userEntersOtp(String option1, String option2, String option3, String option4) {
		WebElement userOtp1Box1 = driver.findElement(otpBox1);
		WebElement userOtp2Box2 = driver.findElement(otpBox2);
		WebElement userOtp2Box3 = driver.findElement(otpBox3);
		WebElement userOtp2Box4 = driver.findElement(otpBox4);
		userOtp1Box1.sendKeys(option1);
		userOtp2Box2.sendKeys(option2);
		userOtp2Box3.sendKeys(option3);
		userOtp2Box4.sendKeys(option4);
	}
	
	
	public void clickVerifyOtp() {
		WebElement verifyOtpButton = driver.findElement(verify_otp);
		verifyOtpButton.click();
	}
	
	public void validateuserShouldNotEnterOtp() {
		WebElement invalidEmailText = driver.findElement(invalidEmail);
		if(invalidEmailText.isDisplayed()) {
			Assert.assertTrue(true,"Warning message is displayed as Invalid mail");
		} else {
			Assert.assertTrue(false, "Warning message is not displayed");
		}
		
	}
	
	public void validateIncorrectOtp() {
		WebElement incorrectOtpMessage = driver.findElement(invalidOtp);
		if(incorrectOtpMessage.isDisplayed()) {
			Assert.assertTrue(true,"Warning message is displayed as Incorrect Otp");
		} else {
			Assert.assertTrue(false, "Warning message is not displayed");
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
