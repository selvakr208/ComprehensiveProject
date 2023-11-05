package assignment;

import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import reusableFunctions.DateManipulation;
import reusableFunctions.StringManipulation;

public class makeMyTrip2 {

	public static void main(String[] args) throws Exception {

		DateManipulation dateManipulation = new DateManipulation();
		StringManipulation stringManipulation = new StringManipulation();

		String departureDateInput = "30 November 2023";
		String returnDateInput = "24 February 2024";

		String departureJourneyMonthYear = stringManipulation.getStringWithIndex(departureDateInput, 2);
		String departureJourneyDate = stringManipulation.getStringWithIndex(departureDateInput, 0, 2);

		// System.out.println("The space removed string
		// "+stringManipulation.getStringWithoutSpace(fromDateInput));
		System.out.println("The string indexed " + departureJourneyMonthYear);

		String returnJourneyMonthYear = stringManipulation.getStringWithIndex(returnDateInput, 2);
		String returnJourneyDate = stringManipulation.getStringWithIndex(returnDateInput, 0, 2);
		System.out.println("The return Journey date is after manipulation " + returnJourneyDate);

		// System.out.println("The space removed string
		// "+stringManipulation.getStringWithoutSpace(fromDateInput));
		System.out.println("The string indexed " + returnJourneyMonthYear);

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://www.makemytrip.com/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='ACCEPT']"))));
		driver.findElement(By.xpath("//*[text()='ACCEPT']")).click();
		Thread.sleep(2000);

		try {
			WebElement flightsLink = driver
					.findElement(By.xpath("//li[@class='menu_Flights']//child::*[text()='Flights']"));
			flightsLink.click();
			Thread.sleep(5000);

			WebElement modalDialogue = driver.findElement(By.xpath("//span[@data-cy='closeModal']"));
			if (modalDialogue.isDisplayed()) {
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//span[@data-cy='closeModal']"))));
				driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();
			}

			WebElement roundTripRadio = driver.findElement(By.xpath("//li[text()='Round Trip']"));
			roundTripRadio.click();
			Thread.sleep(4000);

			WebElement fromLocation = driver.findElement(By.id("fromCity"));
			fromLocation.sendKeys("HYD");
			driver.findElement(By.xpath("//div[text()='HYD']")).click();
			Thread.sleep(5000);

			WebElement toLocation = driver.findElement(By.id("toCity"));
			toLocation.click();
			toLocation.sendKeys("MAA");
			driver.findElement(By.xpath("//div[text()='MAA']")).click();
			Thread.sleep(2000);

			System.out.println("==========> Start of Date picker");
			
			
			int i = 0;
			label1: do {
				List<WebElement> departureMonths = driver
						.findElements(By.xpath("//*[@class='DayPicker-Caption' and @role='heading']/div"));
				System.out.println(departureMonths.size());
				if (departureMonths.size() > 0) {
					for (WebElement month : departureMonths) {
						System.out.println("The text is " + month.getText());
						i = i + 1;
						System.out.println("The j values is " + i);
						if (month.getText()
								.equalsIgnoreCase(stringManipulation.getStringWithoutSpace(departureJourneyMonthYear))) {
							month.click();

							WebElement elem = driver.findElement(By.xpath("//*[@class='DayPicker-Month'][" + i
									+ "]//div[@class='DayPicker-Day']//p[text()='" + departureJourneyDate
									+ "']/ancestor::div[@class='DayPicker-Day' and contains(@aria-label,'"
									+ departureJourneyDate + "')]"));
							elem.click();
							Thread.sleep(2000);
							i = 0;
							System.out.println("The i value after resetting to 0 is " + i);
							break label1;

						}
					}
				}
				if (i >= departureMonths.size()) {
					System.out.println("Im inside the departure month navigation....");
					WebElement toMonth = driver.findElement(By.xpath("//*[@aria-label='Next Month']"));
					toMonth.click();
					i = 0;
					Thread.sleep(2000);
				}
			} while (true);
			

			System.out.println("=================== Return date picker ===== ");

			int j = 0;
			label1: do {
				List<WebElement> returnMonths = driver
						.findElements(By.xpath("//*[@class='DayPicker-Caption' and @role='heading']/div"));
				System.out.println(returnMonths.size());
				if (returnMonths.size() > 0) {
					for (WebElement month : returnMonths) {
						System.out.println("The text is " + month.getText());
						j = j + 1;
						System.out.println("The j values is " + j);
						if (month.getText()
								.equalsIgnoreCase(stringManipulation.getStringWithoutSpace(returnJourneyMonthYear))) {
							month.click();

							WebElement elem = driver.findElement(By.xpath("//*[@class='DayPicker-Month'][" + j
									+ "]//div[@class='DayPicker-Day']//p[text()='" + returnJourneyDate
									+ "']/ancestor::div[@class='DayPicker-Day' and contains(@aria-label,'"
									+ returnJourneyDate + "')]"));
							elem.click();
							Thread.sleep(2000);
							j = 0;
							System.out.println("The j value after resetting to 0 is " + j);
							break label1;

						}
					}
				}
				if (j >= returnMonths.size()) {
					System.out.println("Im inside the month navigation....");
					WebElement toMonth = driver.findElement(By.xpath("//*[@aria-label='Next Month']"));
					toMonth.click();
					j = 0;
					Thread.sleep(2000);
				}
			} while (true);


			Thread.sleep(3000);
			WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
			searchButton.click();

			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.urlContains("search"));
			//wait.until(ExpectedConditions.);
			
			Wait<WebDriver> wait2 = new WebDriverWait(driver, 10);
			wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='OKAY, GOT IT!']"))));
			
			
			if (driver.findElement(By.xpath("//*[text()='OKAY, GOT IT!']")).isDisplayed()) {
				System.out.println("Search page is displayed.");
			} else {
				System.out.println("Search page is not displayed.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("NoSuchElementException occurred and Captured. Search Page not displayed---> " + e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred and Captured. Search Page not displayed---> " + e);
		} finally {
			driver.quit();
		}

	}

}
