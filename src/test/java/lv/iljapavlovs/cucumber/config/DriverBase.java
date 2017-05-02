package lv.iljapavlovs.cucumber.config;

import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;

    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory driverFactory = new DriverFactory();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverFactory.get().getDriver();
    }

    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}