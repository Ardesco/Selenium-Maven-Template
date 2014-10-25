package com.lazerycode.selenium;

import com.lazerycode.selenium.config.DriverType;
import com.lazerycode.selenium.listeners.ScreenshotListener;
import com.lazerycode.selenium.reporting.AllureProperties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lazerycode.selenium.config.DriverType.determineEffectiveDriverType;

@Listeners(ScreenshotListener.class)
public class DriverFactory {

    private static List<WebDriver> webDriverPool = Collections.synchronizedList(new ArrayList<WebDriver>());
    private static ThreadLocal<WebDriver> driverThread;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {

        final DriverType desiredDriver = determineEffectiveDriverType(System.getProperty("browser"));

        driverThread = new ThreadLocal<WebDriver>() {
            @Override
            protected WebDriver initialValue() {
                final WebDriver webDriver = desiredDriver.configureDriverBinaryAndInstantiateWebDriver();
                webDriverPool.add(webDriver);
                return webDriver;
            }
        };
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObject() {
        for (WebDriver driver : webDriverPool) {
            driver.quit();
        }
        AllureProperties.create();
    }
}