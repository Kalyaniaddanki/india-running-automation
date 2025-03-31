package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class EventDetailsPage{
	WebDriver driver;
	
	public static By aboutRace = By.xpath("//*[@class=\"ql-editor ql-blank\"]");
	public static By useDefault = By.xpath("//*[contains(text(),'Use Default')]");
	public static By faqs = By.id("FAQ_text");
	public static By saveAndProceed = By.xpath("//*[contains(text(),'Save and Proceed')]");
	
	public EventDetailsPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void enterAboutRaceDetails() throws InterruptedException {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_UP).perform();
		WebElement enterDetailsRace = driver.findElement(aboutRace);
		enterDetailsRace.sendKeys("Welcome to the Mumbai Monsoon Marathon 2025!\r\n"
				+ "\r\n"
				+ "This race offers a unique opportunity to run along Mumbai’s iconic Marine Drive during the refreshing monsoon season. Whether you're a beginner or a seasoned marathoner, we have multiple categories to suit every fitness level.");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement saveButton = driver.findElement(saveAndProceed);
		js.executeScript("arguments[0].click();", saveButton);
		
	}

}
