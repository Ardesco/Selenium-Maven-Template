package io.iljapavlovs.oldcucumber.core;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

import io.iljapavlovs.oldcucumber.config.ApplicationProperties;
import io.iljapavlovs.oldcucumber.config.ApplicationProperties.ApplicationProperty;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class DriverFactory {

  private RemoteWebDriver driver;
  private DriverType selectedDriverType;

  private final String operatingSystem = System.getProperty("os.name").toUpperCase();
  private final String systemArchitecture = System.getProperty("os.arch");
  private final boolean useRemoteWebDriver = ApplicationProperties.getBoolean(ApplicationProperty.REMOTE_DRIVER);
  private final boolean proxyEnabled = ApplicationProperties.getBoolean(ApplicationProperty.PROXY_ENABLED);
  private final String proxyHostname = ApplicationProperties.getString(ApplicationProperty.PROXY_HOST);
  private final int proxyPort = ApplicationProperties.getInteger(ApplicationProperty.PROXY_PORT);
  private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
  private final String gridUrl = ApplicationProperties.getString(ApplicationProperty.SELENIUM_GRID_URL);
  private final boolean enableVNC = ApplicationProperties.getBoolean(ApplicationProperty.ENABLE_VNC);
  private final boolean enableVideo = ApplicationProperties.getBoolean(ApplicationProperty.ENABLE_VIDEO);

  public DriverFactory() {
    DriverType driverType = DriverType.FIREFOX;
    String browser = ApplicationProperties.getString(ApplicationProperty.BROWSER);
    try {
      driverType = DriverType.valueOf(browser.toUpperCase());
    } catch (IllegalArgumentException ignored) {
      log.warn("Unknown driver specified, defaulting to '" + driverType + "'...");
    } catch (NullPointerException ignored) {
      log.warn("No driver specified, defaulting to '" + driverType + "'...");
    }
    selectedDriverType = driverType;
  }

  public RemoteWebDriver getDriver(){
    if (null == driver) {
      instantiateWebDriver(selectedDriverType);
    }

    return driver;
  }

  public RemoteWebDriver getStoredDriver() {
    return driver;
  }

  public void quitDriver() {
    if (null != driver) {
      driver.quit();
      driver = null;
    }
  }

  private void instantiateWebDriver(DriverType driverType) {
    log.info(" ");
    log.info("Local Operating System: " + operatingSystem);
    log.info("Local Architecture: " + systemArchitecture);
    log.info("Selected Browser: " + selectedDriverType);
    log.info("Connecting to Selenium Grid: " + useRemoteWebDriver);
    log.info(" ");

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    //When running on Selenoid
    desiredCapabilities.setCapability("enableVNC", enableVNC);
    desiredCapabilities.setCapability("enableVideo", enableVideo);

    if (proxyEnabled) {
      Proxy proxy = new Proxy();
      proxy.setProxyType(MANUAL);
      proxy.setHttpProxy(proxyDetails);
      proxy.setSslProxy(proxyDetails);
      desiredCapabilities.setCapability(PROXY, proxy);
    }

    if (useRemoteWebDriver) {
      URL seleniumGridURL = null;
      try {
         seleniumGridURL = new URL(gridUrl);
      } catch (MalformedURLException e) {
        log.error("The seleniumGridURL URL is not well formed: " + e.getMessage());
      }
      String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
      String desiredPlatform = System.getProperty("desiredPlatform");

      if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
        desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
      }

      if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
        desiredCapabilities.setVersion(desiredBrowserVersion);
      }

      desiredCapabilities.setBrowserName(selectedDriverType.toString());
      driver = initRemoteWebdriver(seleniumGridURL, desiredCapabilities);
    } else {
      driver = driverType.getWebDriverObject(desiredCapabilities);
    }
  }


  private RemoteWebDriver initRemoteWebdriver(URL seleniumGridURL, DesiredCapabilities desiredCapabilities) {
    RemoteWebDriver driver;
    int attemptNumber = 0;
    int maxTries = ApplicationProperties.getInteger(ApplicationProperty.SELENIUM_GRID_RETRY_COUNT);

    while (true) {
      try {
        attemptNumber++;
        driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        driver.setFileDetector(new LocalFileDetector());
        break;
      } catch (Exception e) {
        log.error(String
            .format("Remote webdriver init failed. Attempt # - %s. Exception - %s", String.valueOf(attemptNumber),
                e.getClass().getSimpleName()));
        if (attemptNumber == maxTries) {
          throw e;
        }
      }
    }
    return driver;
  }

}

