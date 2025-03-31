package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BombayRunningPage {
	
WebDriver driver;
	
	public static By runningOption = By.xpath("(//*[@class=\"flex items-center\"])[3]");
	public static By allEventDropDown = By.xpath("//*[contains(text(),'All Events')]");
	public static By searchBox = By.name("search");
	public static  By marthonEvent = By.xpath("//*[contains(text(),'Mumbai Marthon')]");
	
	public BombayRunningPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterTheEvent()  {
		driver.get("https://ir-staging.bombayrunning.com/");
		WebElement allEvents = driver.findElement(allEventDropDown);
		allEvents.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(runningOption));
		WebElement running = driver.findElement(runningOption);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", running);
		WebElement input = driver.findElement(searchBox);
		input.click();
		input.sendKeys("Mumbai Marthon", Keys.ENTER);
	}
	
	public void validateTheEventIsPresent() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(marthonEvent));
		
		WebElement mumbaiEvent = driver.findElement(marthonEvent);
		if(mumbaiEvent.isDisplayed()) {
			Assert.assertTrue(true, "The event is ctreated successfully");
		}
		else {
			Assert.assertTrue(false, "The event is not created");
			
		}
		
		
		
	}
	
	
	
	
	

}
