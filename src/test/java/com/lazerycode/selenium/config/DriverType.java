package com.lazerycode.selenium.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.getBoolean;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            if (proxyEnabled) {
                capabilities.setCapability(PROXY, getSeleniumProxyDetails());
            }
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new FirefoxDriver(capabilities);
        }
    },
    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            if (proxyEnabled) {
                capabilities.setCapability(PROXY, getSeleniumProxyDetails());
            }
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new ChromeDriver(capabilities);
        }

        @Override
        public String getWebDriverSystemPropertyKey() {
            return "webdriver.chrome.driver";
        }
    },
    IE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            if (proxyEnabled) {
                capabilities.setCapability(PROXY, getSeleniumProxyDetails());
            }
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }

        @Override
        public String getWebDriverSystemPropertyKey() {
            return "webdriver.ie.driver";
        }
    },
    SAFARI {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            if (proxyEnabled) {
                capabilities.setCapability(PROXY, getSeleniumProxyDetails());
            }
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },
    OPERA {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
            if (proxyEnabled) {
                capabilities.setCapability(PROXY, getSeleniumProxyDetails());
            }
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new OperaDriver(capabilities);
        }

        @Override
        public String getWebDriverSystemPropertyKey() {
            return "webdriver.opera.driver";
        }
    },
    PHANTOMJS {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            capabilities.setCapability("takesScreenshot", true);
            final List<String> cliArguments = new ArrayList<String>();
            cliArguments.add("--web-security=false");
            cliArguments.add("--ssl-protocol=any");
            cliArguments.add("--ignore-ssl-errors=true");
            if (proxyEnabled) {
                cliArguments.add("--proxy-type=http");
                cliArguments.add("--proxy=" + proxyDetails);
            } else {
                cliArguments.add("--proxy-type=none");
            }
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new PhantomJSDriver(capabilities);
        }

        @Override
        public String getWebDriverSystemPropertyKey() {
            return "phantomjs.binary.path";
        }
    };

    public static final DriverType defaultDriverType = FIREFOX;
    public static final boolean useRemoteWebDriver = Boolean.valueOf(System.getProperty("remoteDriver"));
    private static final String operatingSystem = System.getProperties().getProperty("os.name").toUpperCase();
    private static final String systemArchitecture = System.getProperties().getProperty("os.arch");
    public static boolean proxyEnabled = getBoolean("proxyEnabled");
    private static final String proxyDetails = String.format("%s:%s", System.getProperty("proxy"), System.getProperty("proxyPort"));

    public Proxy getSeleniumProxyDetails() {
        Proxy proxy = new Proxy();
        proxy.setProxyType(MANUAL);
        proxy.setHttpProxy(proxyDetails);
        proxy.setSslProxy(proxyDetails);
        return proxy;
    }

    public String getWebDriverSystemPropertyKey() {
        return null;
    }

    public WebDriver instantiateWebDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = getDesiredCapabilities();

        if (useRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            return new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        }

        return getWebDriverObject(desiredCapabilities);
    }

    public static DriverType determineEffectiveDriverType(String browser) {
        DriverType driverType = defaultDriverType;
        try {
            driverType = valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }

        return driverType;
    }

    public WebDriver configureDriverBinaryAndInstantiateWebDriver() {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + this);
        System.out.println(" ");

        try {
            return instantiateWebDriver();
        } catch (MalformedURLException urlIsInvalid) {
            urlIsInvalid.printStackTrace();
            return null;
        }
    }
}