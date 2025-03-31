package com.qa.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EventInformationPage{
	WebDriver driver;

	public static By createEvent = By.xpath("//*[contains(text(),'+ Create Event')]");
	public static By eventName = By.id("Event Name_text");
	public static By onGround = By.xpath("//*[contains(text(),'On-Ground')]");
	public static By virtual = By.xpath("//*[contains(text(),'Virtual')]");
	public static By ongroundAndVirtual = By.xpath("//*[contains(text(),'On-Ground+Virtual')]");
	public static By eventStartDate = By.id("event_start_date");
	public static By eventEndDate = By.id("event_end_date");
	public static By location = By.xpath("//*[@placeholder=\"Search location here\"]");
	public static By location_from_dropDown = By.xpath("//*[@class=\"w-full h-64 pt-10\"]");
	public static By address = By.id("Address_text");
	public static By area = By.id("Area_text");
	public static By city = By.id("City_text");
	public static By state = By.name("state");
	public static By pinCode = By.name("pin_code");
	public static By saveAndProceed = By.xpath("//*[contains(text(),'Save and Proceed')]");
	public static By profileText = By.xpath("//*[contains(text(),'Profile')]");
	public static By eventsText = By.xpath("//*[contains(text(),'Events')]");
	public static By dashboardText = By.xpath("(//*[contains(text(),'Dashboard')])[2]");
	public static By myEventsText = By.xpath("//*[contains(text(),'My Events')]");
	public static By eventNameRequired = By.xpath("(//p)[7]");
	
	
	public EventInformationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public  void clickonCreateEvent() {
		WebElement createEventButton = driver.findElement(createEvent);
		createEventButton.click();
	}
	
	public void enterEventInformation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		WebElement nameOfTheEvent = driver.findElement(eventName);
		nameOfTheEvent.sendKeys("Mumbai Monsoon Marathon 2025");
		WebElement onGroundRadioButton = driver.findElement(onGround);
		onGroundRadioButton.click();
	}
	
	public void enterInvalidInformation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		WebElement virtualRadioButton = driver.findElement(virtual);
		virtualRadioButton.click();
	}
	
	public void enterStartDateAndEndDate() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(eventStartDate));

		WebElement startDate = driver.findElement(eventStartDate);

		// Generate date in the format Firefox expects: yyyy-MM-dd
		LocalDate date = LocalDate.now();
		LocalDate futureDate = date.plusDays(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String startDateEvent = futureDate.format(formatter);

		System.out.println("Setting date: " + startDateEvent);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(
			    "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
			    "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
			    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
			    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
			    "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
			    startDate,
			    startDateEvent
			);
	}

	public void clickSaveAndProceedWithouEnteringEventName() {
		WebElement saveAndProceedButton = driver.findElement(saveAndProceed);
		saveAndProceedButton.click();
		WebElement eventRequired = driver.findElement(eventNameRequired);
		Assert.assertEquals(eventRequired.getText(), "Event Name is required");
		
		
	}
	
	public void ensterLocationAndAdreess() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(location));
		WebElement locationTextBox = driver.findElement(location);
		locationTextBox.sendKeys("Marine Drive, Churchgate, Mumbai, Maharashtra 400020, India",Keys.ENTER);
		locationTextBox.sendKeys("   ",Keys.ENTER);
		locationTextBox.click();
		WebElement selectOption = driver.findElement(location_from_dropDown);
		selectOption.click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		WebElement saveAndProceedButton = driver.findElement(saveAndProceed);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", saveAndProceedButton);
	}
	
	public void validateUserIsLoginedSuccessfully() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileText));
		WebElement profile = driver.findElement(profileText);
		WebElement events = driver.findElement(eventsText);
		WebElement dashboard = driver.findElement(dashboardText);
		WebElement myEvents = driver.findElement(myEventsText);
		if(profile.isDisplayed()&& events.isDisplayed() && dashboard.isDisplayed() && myEvents.isDisplayed()) {
			Assert.assertTrue(true, "The user is logined suucessfully into my running application");
		} else {
			Assert.assertTrue(false, "The user is not login into the application");
		}
	}
	
	

}
