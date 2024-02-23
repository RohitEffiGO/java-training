package com.scenario.automate.utils;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileOperations {

	public boolean uploadFileUsingElement(WebElement element, String filePathWithFileName) {

		/*
		 * If this method returns false then file path along with file name must be
		 * wrong or file directory is being passed instead of file.
		 * 
		 * Also rememeber if file from c:// is being passed then this method will always
		 * return false.
		 */

		File file = new File(filePathWithFileName);
		if (element != null && file.isFile()) {
			element.sendKeys(file.getAbsolutePath());
			return true;
		}

		return false;
	}

	
/*****
	 * To download in custom path pre config the driver using
	 * 
	 * String downloadFilePath = "<some absolute path>";
	 * chromePrefs.put("download.default_directory", downloadFilepath);
	 * chromePrefs.put("download.prompt_for_download", false);
	 * chromePrefs.put("download.extensions_to_open", "application/txt");
	 * chromePrefs.put("safebrowsing.enabled", true);
	 * 
	 * options.addArguments("--safebrowsing-disable-download-protection");
	 * options.addArguments("safebrowsing-disable-extension-blacklist");
	 * options.setExperimentalOption("prefs", chromePrefs);
	 * 
	 * Now pass this options object to the ChromeDriver initialization.
******/
	
	public boolean downloadFileUsingElement(WebElement element, WebDriverWait wait, int seconds)
			throws InterruptedException {

		if (element != null) {

			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();

			Thread.sleep(seconds * 1000);
			return true;
		}
		return false;
	}

	public boolean handlePopUp() {
		return false;
	}
}
