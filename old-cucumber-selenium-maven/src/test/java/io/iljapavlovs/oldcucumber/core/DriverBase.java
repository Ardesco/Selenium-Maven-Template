package io.iljapavlovs.oldcucumber.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class DriverBase {
    //this pool is redundant since it will be screated and destryed before and after each test
    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactoryThread;

    public static void instantiateDriverObject() {
        driverFactoryThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            webDriverThreadPool.add(driverFactory);
            return driverFactory;
        });
    }

    public static WebDriver getDriver(){
        if (!isWebdriverInitialized()) {
            instantiateDriverObject();
        }
        return driverFactoryThread.get().getDriver();
    }

    public static boolean isWebdriverInitialized() {
        return driverFactoryThread != null;
    }

    public static void clearCookies() {
        try {
            driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            log.warn("Unable to clear cookies, driver object is not viable...");
        }
    }

    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}
