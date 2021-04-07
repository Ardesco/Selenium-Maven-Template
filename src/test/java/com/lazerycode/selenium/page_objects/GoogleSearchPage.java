package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.helpers.CustomExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class GoogleSearchPage {

    private final WebDriverWait wait;
    private final RemoteWebDriver driver;

    public GoogleSearchPage() throws Exception {
        driver = DriverBase.getDriver();
        initQueryObjects(this, driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(100));
    }

    public void waitForPageTitleToStartWith(String someText) {
        wait.until(CustomExpectedConditions.pageTitleStartsWith(someText));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
