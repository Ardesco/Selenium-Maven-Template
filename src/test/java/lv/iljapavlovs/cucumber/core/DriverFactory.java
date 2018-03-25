package lv.iljapavlovs.cucumber.core;

import lombok.extern.slf4j.Slf4j;
import lv.iljapavlovs.cucumber.config.ApplicationProperties;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.BROWSER;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.DESIRED_BROWSER_VERSION;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.DESIRED_PLATFORM;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.PROXY_ENABLED;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.PROXY_HOST;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.PROXY_PORT;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.REMOTE_DRIVER;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.SELENIUM_GRID_RETRY_COUNT;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.SELENIUM_GRID_URL;
import static lv.iljapavlovs.cucumber.core.DriverType.FIREFOX;
import static lv.iljapavlovs.cucumber.core.DriverType.valueOf;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

@Slf4j
public class DriverFactory {
    private final DriverType defaultDriverType = FIREFOX;
    private final String browser = ApplicationProperties.getString(BROWSER).toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = ApplicationProperties.getBoolean(REMOTE_DRIVER);
    private final boolean proxyEnabled = ApplicationProperties.getBoolean(PROXY_ENABLED);
    private final String proxyHostname = ApplicationProperties.getString(PROXY_HOST);
    private final Integer proxyPort = ApplicationProperties.getInteger(PROXY_PORT);
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);

    private WebDriver webdriver;
    private DriverType selectedDriverType;

    public WebDriver getDriver() {
        if (null == webdriver) {
            Proxy proxy = null;
            if (proxyEnabled) {
                proxy = new Proxy();
                proxy.setProxyType(MANUAL);
                proxy.setHttpProxy(proxyDetails);
                proxy.setSslProxy(proxyDetails);
            }
            determineEffectiveDriverType();
            MutableCapabilities mutableCapabilities = selectedDriverType.getDesiredCapabilities(proxy);
            instantiateWebDriver(mutableCapabilities);
        }

        return webdriver;
    }

    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
            webdriver = null;
        }
    }

    private void determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            log.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            log.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    private void instantiateWebDriver(MutableCapabilities mutableCapabilities) {
        log.info(" ");
        log.info("Current Operating System: " + operatingSystem);
        log.info("Current Architecture: " + systemArchitecture);
        log.info("Current Browser Selection: " + selectedDriverType);
        log.info(" ");

        if (useRemoteWebDriver) {
            URL seleniumGridURL = null;
            try {
                seleniumGridURL = new URL(ApplicationProperties.getString(SELENIUM_GRID_URL));
            } catch (MalformedURLException e) {
                log.error("The seleniumGridURL URL is not well formed: " + e.getMessage());
            }
            String desiredBrowserVersion = ApplicationProperties.getString(DESIRED_BROWSER_VERSION);
            String desiredPlatform = ApplicationProperties.getString(DESIRED_PLATFORM);
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.merge(mutableCapabilities);

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            webdriver = initRemoteWebdriver(seleniumGridURL, desiredCapabilities);
        } else {
            webdriver = selectedDriverType.getWebDriverObject(mutableCapabilities);
        }
    }


    private RemoteWebDriver initRemoteWebdriver(URL seleniumGridURL, DesiredCapabilities desiredCapabilities) {
        RemoteWebDriver driver;
        int attemptNumber = 0;
        int maxTries = ApplicationProperties.getInteger(SELENIUM_GRID_RETRY_COUNT);

        while (true) {
            try {
                attemptNumber++;
                driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
                driver.setFileDetector(new LocalFileDetector());
                break;
            } catch (Exception e) {
                log.error(String.format("Remote webdriver init failed. Attempt # - %s. Exception - %s", String.valueOf(attemptNumber), e.getClass().getSimpleName()));
                if (attemptNumber == maxTries) throw e;
            }
        }
        return driver;
    }

}

