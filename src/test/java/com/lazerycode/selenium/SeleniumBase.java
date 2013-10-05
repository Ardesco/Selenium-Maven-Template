package com.lazerycode.selenium;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.*;

import static com.lazerycode.selenium.BrowserType.FIREFOX;

public class SeleniumBase {

  private static ResourceBundle _prop = ResourceBundle.getBundle("dev");
  private static BrowserType browserType;
  private static List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());
  private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>() {

    @Override
    protected WebDriver initialValue() {
      WebDriver driver = loadWebDriver();
      webDrivers.add(driver);
      return driver;
    }
  };

  @BeforeSuite
  public static void setUpTest() {
    for (BrowserType browser : BrowserType.values()) {
      if (browser.toString().toLowerCase().equals(_prop.getString("browser").toLowerCase())) {
        browserType = browser;
      }
    }
    if (browserType == null) {
      System.err.println("Unknown browser specified, defaulting to 'Firefox'...");
      browserType = FIREFOX;
    }
  }

  @AfterSuite
  public static void tearDown() {
    for (WebDriver driver : webDrivers) {
      driver.quit();
    }
  }

  @AfterMethod
  public static void clearCookies() {
    getDriver().manage().deleteAllCookies();
  }

  protected static WebDriver getDriver() {
    return driverForThread.get();
  }

  private static DesiredCapabilities generateDesiredCapabilities(BrowserType capabilityType) {
    DesiredCapabilities capabilities;

    switch (capabilityType) {
      case IE:
        capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        capabilities.setCapability("requireWindowFocus", true);
        break;
      case SAFARI:
        capabilities = DesiredCapabilities.safari();
        capabilities.setCapability("safari.cleanSession", true);
        break;
      case OPERA:
        capabilities = DesiredCapabilities.opera();
        capabilities.setCapability("opera.arguments", "-nowin -nomail");
        break;
      case GHOSTDRIVER:
        capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability("takesScreenshot", true);
        //Need to set an environmental variable that points to the location of the PhantomJS Binary for GhostDriver support
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("PHANTOMJS_BINARY"));
        break;
      case CHROME:
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
        HashMap<String, String> chromePreferences = new HashMap<String, String>();
        chromePreferences.put("profile.password_manager_enabled", "false");
        capabilities.setCapability("chrome.prefs", chromePreferences);
        break;
      case FIREFOX:
        capabilities = DesiredCapabilities.firefox();
        break;
      case HTMLUNIT:
      default:
        capabilities = DesiredCapabilities.htmlUnit();
        capabilities.setCapability("javascriptEnabled", "true");
    }

    return capabilities;
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
            System.setProperty("webdriver.chrome.driver", _prop.getString("binaryRootFolder") + "/windows/googlechrome/64bit/2.4/chromedriver.exe");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", _prop.getString("binaryRootFolder") + "/osx/googlechrome/64bit/2.4/chromedriver");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", _prop.getString("binaryRootFolder") + "/linux/googlechrome/64bit/2.4/chromedriver");
          }
        } else {
          if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver", _prop.getString("binaryRootFolder") + "/windows/googlechrome/32bit/2.4/chromedriver.exe");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", _prop.getString("binaryRootFolder") + "/osx/googlechrome/32bit/2.4/chromedriver");
          } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", _prop.getString("binaryRootFolder") + "/linux/googlechrome/32bit/2.4/chromedriver");
          }
        }
        break;
      case IE:
        if (System.getProperties().getProperty("os.arch").toLowerCase().equals("x86_64") || System.getProperties().getProperty("os.arch").toLowerCase().equals("amd64")) {
          System.setProperty("webdriver.ie.driver", _prop.getString("binaryRootFolder") + "/windows/internetexplorer/64bit/2.29.0/IEDriverServer.exe");
        } else {
          System.setProperty("webdriver.ie.driver", _prop.getString("binaryRootFolder") + "/windows/internetexplorer/32bit/2.29.0/IEDriverServer.exe");
        }
        break;
    }

    //Instantiate driver object
    switch (browserType) {
      case FIREFOX:
        return new FirefoxDriver(generateDesiredCapabilities(browserType));
      case CHROME:
        return new ChromeDriver(generateDesiredCapabilities(browserType));
      case IE:
        return new InternetExplorerDriver(generateDesiredCapabilities(browserType));
      case SAFARI:
        return new SafariDriver(generateDesiredCapabilities(browserType));
      case OPERA:
        return new OperaDriver(generateDesiredCapabilities(browserType));
      case GHOSTDRIVER:
        return new PhantomJSDriver(generateDesiredCapabilities(browserType));
      default:
        return new HtmlUnitDriver(generateDesiredCapabilities(browserType));
    }
  }
}
