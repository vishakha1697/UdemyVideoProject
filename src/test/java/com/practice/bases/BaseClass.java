package com.practice.bases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.practice.utilities.ExcelReader;
import com.practice.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/DataFile.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setup() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Or.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("chrome is starting");

			}

			driver.get(config.getProperty("testSiteUrl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

		}

	}

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(or.getProperty(locator))).click();

		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(or.getProperty(locator))).click();

		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(or.getProperty(locator))).click();

		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(or.getProperty(locator))).click();

		} else {

			test.log(LogStatus.INFO, " Clicking on : " + locator);

		}
	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	
	  public void forceWait() {
	  
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	  
	  }
	
	 

	public void actions(String locator) {

		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath(or.getProperty(locator)))).build().perform();

	}


	
	
	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);

		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);

		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);

		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
		} else {

			test.log(LogStatus.INFO, "Typing values in : " + locator + " Entered value as : " + value);

		}
	}

	@AfterSuite
	public void teardown() {

		if (driver != null) {
			driver.quit();

		}

	}

}
