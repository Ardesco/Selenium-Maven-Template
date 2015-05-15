package com.lazerycode.selenium;

import com.lazerycode.selenium.config.DriverType;
import com.lazerycode.selenium.listeners.ScreenshotListener;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverFactory {

    private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
    private static ThreadLocal<WebDriverThread> driverThread;

    public DriverFactory() {
        setBinaryVariables();
    }

    private void setBinaryVariables() {
        for (DriverType driverType : DriverType.values()) {
            String variable = driverType.getWebDriverSystemPropertyKey();
            if (null != variable) {
                String systemProperty = System.getProperty(variable);
                if (null == systemProperty || systemProperty.isEmpty()) {
                    String environmentalVariable = System.getenv(variable);
                    if (null != environmentalVariable && !environmentalVariable.isEmpty()) {
                        System.setProperty(variable, environmentalVariable);
                    }
                }
            }
        }
    }

    @BeforeSuite
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getDriver();
    }

    public static WebDriver getBrowserMobProxyEnabledDriver() throws Exception {
        return driverThread.get().getBrowserMobProxyEnabledDriver();
    }

    public static BrowserMobProxy getBrowserMobProxy() {
        return driverThread.get().getBrowserMobProxy();
    }

    @AfterMethod
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public static void closeDriverObject() {
        for (WebDriverThread webDriverThread : webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }
}