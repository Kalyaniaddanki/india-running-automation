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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TicketPage{
	WebDriver driver;
	
	
	public static By runningRadioButton = By.id("Running");
	public static By cyclingRadioButton = By.id("Cycling");
	public static By nameOfCategory = By.id("Name of the Category_text");
	public static By distance = By.id("tickets.0.distance");
	public static By additionalInformation = By.id("Additional Information_text");
	public static By basePrice = By.id("Base Price");
	public static By ageLimitRegistration = By.id("Age Limit for Registration");
	public static By maximumAge = By.xpath("//*[@placeholder=\"Maximum Age (optional)\"]");
	public static By inclusives = By.id("search_input");
	public static By medals = By.xpath("//*[contains(text(),'Medals')]");
	public static By refreshments = By.xpath("//*[contains(text(),'Refreshments')]");
	public static By registrationStartDate = By.id("tickets.0.reg_start_date");
	public static By registrationStartTime = By.name("tickets.0.reg_start_time");
	public static By yes = By.id("Yes");
	public static By no = By.id("No");
	public static By updateTicket = By.xpath("//button");
	public static By publishEvent = By.xpath("//*[contains(text(),'Publish Now')]");
	public static By saveAsDraft = By.xpath("//*[contains(text(),'Save as draft')]");
	public static By yes_comfirm = By.xpath("//*[contains(text(),'Yes, confirm')]");
	public static By proceedToCreateButton = By.xpath("//*[contains(text(),'Proceed to create form')]");
	public static By eventIsLive = By.xpath("//*[@class=\"text-xl font-normal text-gray-400\"]");

	
	
	public TicketPage(WebDriver driver) {
		this.driver = driver;		
	}
	
	public void clickRunningAndNameOfCategory() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_UP).perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(runningRadioButton));
		WebElement runinngButton = driver.findElement(runningRadioButton);
		runinngButton.click();
		WebElement distanceSelected = driver.findElement(distance);
		Select selectOption  = new Select(distanceSelected);
		selectOption.selectByValue("10 Kilometers");
		WebElement category = driver.findElement(nameOfCategory);
		category.sendKeys("Half Marthon 10k");
	}
	
	public void enterTicketPriceAndAge() throws InterruptedException {
		WebElement ticketPrice = driver.findElement(basePrice);
		ticketPrice.sendKeys("1000");
		WebElement ageLimit = driver.findElement(ageLimitRegistration);
		ageLimit.sendKeys("10");
		WebElement maxAgeRequired = driver.findElement(maximumAge);
		maxAgeRequired.sendKeys("50");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		WebElement startDateRegistration = driver.findElement(registrationStartDate);
		WebElement startTimeRegistration = driver.findElement(registrationStartTime);
		startTimeRegistration.sendKeys("09:00");
		WebElement inclusives_options = driver.findElement(inclusives);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", inclusives_options);
		WebElement option1 = driver.findElement(medals);
		option1.click();
		WebElement option2 = driver.findElement(refreshments);
		option2.click();
		maxAgeRequired.click();
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		LocalDate futureDate = LocalDate.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = futureDate.format(formatter);
		js.executeScript(
			    "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
			    "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
			    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
			    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
			    "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
			    startDateRegistration,
			    formattedDate
			);

		// Optional: print what value is set now
		String valueSet = startDateRegistration.getAttribute("value");
		System.out.println("Date set in field: " + valueSet);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		WebElement createTicketButton = driver.findElement(updateTicket);
		js.executeScript("arguments[0].click();", createTicketButton);
	}
	
	public void proceedToCreateForm() throws InterruptedException {
		WebElement proceedToCreate = driver.findElement(proceedToCreateButton);
		proceedToCreate.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(publishEvent));
		WebElement publishNow = driver.findElement(publishEvent);
		publishNow.click();
		wait.until(ExpectedConditions.elementToBeClickable(yes_comfirm));
		WebElement yesButton = driver.findElement(yes_comfirm);
		yesButton.click();
		
	}
	
	public void validateEventIscreatedSuccessfully() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement congragulationText = wait.until(ExpectedConditions.visibilityOfElementLocated(eventIsLive));
		Assert.assertEquals(congragulationText.getText(), "Congratulations your event is live");
	}
	
	
	
	
	
	
	

}
