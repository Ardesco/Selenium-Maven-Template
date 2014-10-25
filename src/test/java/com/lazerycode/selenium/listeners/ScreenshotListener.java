package com.lazerycode.selenium.listeners;

import static com.lazerycode.selenium.DriverFactory.getDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;

public class ScreenshotListener extends TestListenerAdapter {

	private boolean createFile(File screenshot) {
		boolean fileCreated = false;

		if (screenshot.exists()) {
			fileCreated = true;
		} else {
			File parentDirectory = new File(screenshot.getParent());
			if (parentDirectory.exists() || parentDirectory.mkdirs()) {
				try {
					fileCreated = screenshot.createNewFile();
				} catch (IOException errorCreatingScreenshot) {
					errorCreatingScreenshot.printStackTrace();
				}
			}
		}

		return fileCreated;
	}

	@Attachment(value = "Screenshot on failure", type = "image/png")
	private byte[] writeScreenshotToFile(WebDriver driver, File screenshot) {
		try {
			FileOutputStream screenshotStream = new FileOutputStream(screenshot);
			byte[] bytes = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			screenshotStream.write(bytes);
			screenshotStream.close();
			return bytes;
		} catch (IOException unableToWriteScreenshot) {
			System.err.println("Unable to write "
					+ screenshot.getAbsolutePath());
			unableToWriteScreenshot.printStackTrace();
		}
		return null;
	}

	@Override
	public void onTestFailure(ITestResult failingTest) {
		WebDriver driver = getDriver();
		String screenshotDirectory = System.getProperty("screenshotDirectory");
		String screenshotAbsolutePath = screenshotDirectory + File.separator
				+ System.currentTimeMillis() + "_" + failingTest.getName()
				+ ".png";
		File screenshot = new File(screenshotAbsolutePath);
		if (createFile(screenshot)) {
			try {
				writeScreenshotToFile(driver, screenshot);
			} catch (ClassCastException weNeedToAugmentOurDriverObject) {
				writeScreenshotToFile(new Augmenter().augment(driver),
						screenshot);
			}
			System.out.println("Written screenshot to "
					+ screenshotAbsolutePath);
		} else {
			System.err.println("Unable to create " + screenshotAbsolutePath);
		}
	}
}