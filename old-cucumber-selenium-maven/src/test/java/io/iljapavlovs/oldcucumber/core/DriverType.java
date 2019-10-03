package io.iljapavlovs.oldcucumber.core;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.iljapavlovs.oldcucumber.config.ApplicationProperties;
import io.iljapavlovs.oldcucumber.config.ApplicationProperties.ApplicationProperty;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            if(!IS_REMOTE_DRIVER){
                WebDriverManager.firefoxdriver().setup();
            }

            FirefoxOptions options = new FirefoxOptions();
            options.merge(capabilities);
            options.setHeadless(HEADLESS);

            return new FirefoxDriver(options);
        }
    },
    CHROME {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            if(!IS_REMOTE_DRIVER){
                WebDriverManager.chromedriver().setup();
            }

            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            options.setHeadless(HEADLESS);
            options.addArguments("--no-default-browser-check");
            options.setExperimentalOption("prefs", chromePreferences);

            return new ChromeDriver(options);
        }
    },
    IE {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            if(!IS_REMOTE_DRIVER){
                WebDriverManager.iedriver().setup();
            }

            InternetExplorerOptions options = new InternetExplorerOptions();
            options.merge(capabilities);
            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

            return new InternetExplorerDriver(options);
        }

        @Override
        public String toString() {
            return "internet explorer";
        }
    },
    EDGE {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            if(!IS_REMOTE_DRIVER){
                WebDriverManager.edgedriver().setup();
            }

            EdgeOptions options = new EdgeOptions();
            options.merge(capabilities);

            return new EdgeDriver(options);
        }
    },
    SAFARI {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {

            SafariOptions options = new SafariOptions();
            options.merge(capabilities);

            return new SafariDriver(options);
        }
    },
    OPERA {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            if(!IS_REMOTE_DRIVER){
                WebDriverManager.operadriver().setup();
            }

            OperaOptions options = new OperaOptions();
            options.merge(capabilities);

            return new OperaDriver(options);
        }
    };

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public final static boolean IS_REMOTE_DRIVER =  ApplicationProperties.getBoolean(ApplicationProperty.REMOTE_DRIVER);
    public final static boolean HEADLESS =  ApplicationProperties.getBoolean(ApplicationProperty.HEADLESS);
}
