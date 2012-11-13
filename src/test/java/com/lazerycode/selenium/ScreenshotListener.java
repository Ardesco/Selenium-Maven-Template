package com.lazerycode.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenshotListener extends TestListenerAdapter {

  @Override
  public void onTestFailure(ITestResult tr) {
    File screenshot = new File("screenshots" + File.separator + System.currentTimeMillis() + "_" + tr.getName() + ".png");
    if (!screenshot.exists()) {
      new File(screenshot.getParent()).mkdirs();
      try {
        screenshot.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      new FileOutputStream(screenshot).write(((TakesScreenshot) SeleniumBase.getDriver()).getScreenshotAs(OutputType.BYTES));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Written screenshot to " + screenshot.getAbsolutePath());
  }
}

