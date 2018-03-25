package lv.iljapavlovs.cucumber.core;

import lv.iljapavlovs.cucumber.config.ApplicationProperties;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.HashMap;

import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.CHROME_DRIVER_PATH;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.FIREFOX_BINARY_PATH;
import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.GECKO_DRIVER_PATH;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("marionette", true);
            return addProxySettings(firefoxOptions, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            System.setProperty("webdriver.firefox.bin", ApplicationProperties.getString(FIREFOX_BINARY_PATH));
            System.setProperty("webdriver.gecko.driver", ApplicationProperties.getString(GECKO_DRIVER_PATH));
            return new FirefoxDriver(capabilities);
        }
    },

    FIREFOX_HEADLESS {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            // firefoc binary should be set before initializing FirefoxBinary
            System.setProperty("webdriver.firefox.bin", ApplicationProperties.getString(FIREFOX_BINARY_PATH));

            FirefoxBinary firefoxBinary = new FirefoxBinary();
            firefoxBinary.addCommandLineOptions("--headless");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("marionette", true);
            //does not work for some reason, need to explicitly add command line option
            firefoxOptions.setHeadless(true);
            firefoxOptions.setBinary(firefoxBinary);
            return addProxySettings(firefoxOptions, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            System.setProperty("webdriver.gecko.driver", ApplicationProperties.getString(GECKO_DRIVER_PATH));
            return new FirefoxDriver(capabilities);
        }
    },
    CHROME {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            System.setProperty("webdriver.chrome.driver", ApplicationProperties.getString(CHROME_DRIVER_PATH));
            return new ChromeDriver(capabilities);
        }
    },
    IE {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    },
    EDGE {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new EdgeDriver(capabilities);
        }
    },
    SAFARI {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },
    CHROME_HEADLESS {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            HashMap<String, String> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            capabilities.setCapability("chrome.prefs", chromePreferences);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            System.setProperty("webdriver.chrome.driver", ApplicationProperties.getString(CHROME_DRIVER_PATH));
            return new ChromeDriver(capabilities);
        }
    };

    protected MutableCapabilities addProxySettings(MutableCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }

        return capabilities;
    }

}