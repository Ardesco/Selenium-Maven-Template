package com.lazerycode.selenium;

import com.lazerycode.selenium.config.DriverType;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import static com.lazerycode.selenium.config.DriverType.FIREFOX;
import static com.lazerycode.selenium.config.DriverType.valueOf;
import static net.lightbody.bmp.client.ClientUtil.createSeleniumProxy;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

public class WebDriverThread {

    private WebDriver webdriver;
    private DriverType selectedDriverType;
    private BrowserMobProxy browserMobProxy;
    private boolean usingBrowserMobProxy = false;

    private final DriverType defaultDriverType = FIREFOX;
    private final String browser = System.getProperty("browser").toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    private final String proxyHostname = System.getProperty("proxyHost");
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);

    public WebDriver getDriver() throws Exception {
        return getDriver(usingBrowserMobProxy);
    }

    public WebDriver getBrowserMobProxyEnabledDriver() throws Exception {
        return getDriver(true);
    }

    public BrowserMobProxy getBrowserMobProxy() {
        if (usingBrowserMobProxy) {
            return browserMobProxy;
        }
        return null;
    }

    private WebDriver getDriver(boolean useBrowserMobProxy) throws Exception {
        if (null != webdriver && usingBrowserMobProxy != useBrowserMobProxy) {
            webdriver.quit();
            webdriver = null;
        }
        if (null == webdriver) {
            Proxy proxy = null;
            if (proxyEnabled || useBrowserMobProxy) {
                if (useBrowserMobProxy) {
                    usingBrowserMobProxy = true;
                    browserMobProxy = new ProxyServer();
                    browserMobProxy.start();
                    if (proxyEnabled) {
                        browserMobProxy.setChainedProxy(new InetSocketAddress(proxyHostname, proxyPort));
                    }
                    proxy = createSeleniumProxy(browserMobProxy);
                } else {
                    proxy = new Proxy();
                    proxy.setProxyType(MANUAL);
                    proxy.setHttpProxy(proxyDetails);
                    proxy.setSslProxy(proxyDetails);
                }
            }
            determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities(proxy);
            instantiateWebDriver(desiredCapabilities);
        }

        return webdriver;
    }

    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
        }
        if (usingBrowserMobProxy) {
            browserMobProxy.abort();
            usingBrowserMobProxy = false;
        }
    }

    private void determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");

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

            webdriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
        }
    }
}
