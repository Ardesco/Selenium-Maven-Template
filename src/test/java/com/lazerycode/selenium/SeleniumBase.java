package com.lazerycode.selenium;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeleniumBase {
  public static BrowserType browserType = BrowserType.FIREFOX;
  private final static long MULTI_THREAD_START_UP_DELAY = 5000;

  private static List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());
  private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>() {

    @Override
    protected WebDriver initialValue() {
      if (webDrivers.size() > 0) {
        try {
          Thread.sleep(MULTI_THREAD_START_UP_DELAY);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      WebDriver driver = loadWebDriver();
      webDrivers.add(driver);
      return driver;
    }
  };

  @BeforeSuite
  public static void setUpTest() {
    //TODO read in browser from POM
  }

  @AfterSuite
  public static void tearDown() {
    for (WebDriver driver : webDrivers) {
      driver.quit();
    }
  }

  private static WebDriver loadWebDriver() {
    System.out.println("Current Operating System: " + System.getProperties().getProperty("os.name"));
    System.out.println("Current Architecture: " + System.getProperties().getProperty("os.arch"));
    System.out.println("Current Browser Selection: " + browserType);

    //Load standalone executable if required
    switch (browserType) {
      case CHROME:
        if (System.getProperties().getProperty("os.arch").toLowerCase().equals("x86_64") || System.getProperties().getProperty("os.arch").toLowerCase().equals("amd64")) {
          if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "selenium_standalone_binaries/googlechrome/windows/64bit/22/chromedriver.exe");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "selenium_standalone_binaries/googlechrome/osx/64bit/21/chromedriver");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "selenium_standalone_binaries/googlechrome/linux/64bit/21/chromedriver");
          }
        } else {
          if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "selenium_standalone_binaries/googlechrome/windows/32bit/22/chromedriver.exe");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "selenium_standalone_binaries/googlechrome/osx/32bit/21/chromedriver");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "selenium_standalone_binaries/googlechrome/linux/32bit/21/chromedriver");
          }
        }
        break;
      case IE:
        if (System.getProperties().getProperty("os.arch").toLowerCase().equals("x86_64") || System.getProperties().getProperty("os.arch").toLowerCase().equals("amd64")) {
          System.setProperty("webdriver.ie.driver", "selenium_standalone_binaries/internetexplorer/windows/64bit/2.25.2/IEDriverServer.exe");
        } else {
          System.setProperty("webdriver.ie.driver", "selenium_standalone_binaries/internetexplorer/windows/32bit/2.25.2/IEDriverServer.exe");
        }
        break;
    }

    //Instantiate driver object
    switch (browserType) {
      case FIREFOX:
        return new FirefoxDriver();
      case CHROME:
        DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
        chromeCaps.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));

        return new ChromeDriver(chromeCaps);
      case IE:

        DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
        ieCaps.setCapability("enablePersistantHover", false);

        return new InternetExplorerDriver(ieCaps);
      case SAFARI:
        DesiredCapabilities safariCaps = DesiredCapabilities.safari();
        safariCaps.setCapability("safari.cleanSession", true);

        return new SafariDriver(safariCaps);
      case OPERA:
        DesiredCapabilities operaCaps = DesiredCapabilities.opera();
        operaCaps.setCapability("opera.arguments", "-nowin -nomail");

        return new OperaDriver(operaCaps);
      default:
        DesiredCapabilities htmlUnitCaps = DesiredCapabilities.htmlUnit();
        htmlUnitCaps.setCapability("javascriptEnabled", "true");

        return new HtmlUnitDriver(htmlUnitCaps);
    }
  }

  protected static WebDriver getDriver() {
    return driverForThread.get();
  }
}
