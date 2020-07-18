package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.GoogleHomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GoogleExampleIT extends DriverBase {

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

    @Test
    public void apple() throws Exception {
        WebDriver driver = getDriver();
        driver.get("http://www.apple.com");

        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith("apple"));

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

//        driver.findElement(By.cssSelector(".ac-gn-link-bag"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/div/ul[2]/li[10]/div/a"))).click();

        Thread.sleep(4000);
    }
}