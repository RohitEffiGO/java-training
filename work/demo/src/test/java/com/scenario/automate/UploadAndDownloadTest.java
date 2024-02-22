package com.scenario.automate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;
import com.scenario.automate.utils.FileOperations;

@SpringBootTest
public class UploadAndDownloadTest {
	ChromeOptions options;
	WebDriver driver;
	ElementAction elementAction;
	FileOperations ops;

	@BeforeTest
	public void loadEverything() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Login\\work\\demo\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");

		this.options = new ChromeOptions();
		options.addArguments("incognito");
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		String downloadFilepath = "D:\\Login\\work\\demo\\src\\main\\resources\\files";
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("download.prompt_for_download", false);

		options.setExperimentalOption("prefs", chromePrefs);
		this.driver = new ChromeDriver(options);
		this.elementAction = new ElementAction();
		this.ops = new FileOperations();
	}

	@Test
	public void testUpload() {
		driver.get("https://demoqa.com/upload-download");
		WebElement element = elementAction.getTheElement(driver, By.cssSelector("#uploadFile"));
		assertNotEquals(null, element);
		assertEquals(true, ops.uploadFileUsingElement(element, "src/main/resources/files/consumer.py"));
	}

	@Test
	public void testDownload() throws InterruptedException {
		driver.get("https://www.stats.govt.nz/large-datasets/csv-files-for-download/");
		WebElement element = elementAction.getTheElement(this.driver, By.cssSelector(
				"div.l12:nth-child(2) > article:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)"));

		assertNotEquals(null, element);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"div.l12:nth-child(2) > article:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)")));

		assertEquals(true, ops.downloadFileUsingElement(element, "src/main/resources/files/", wait));

		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}

	@AfterTest
	public void destroyEverything() {

		driver.close();
	}
}
