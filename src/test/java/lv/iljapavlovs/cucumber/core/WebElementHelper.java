package lv.iljapavlovs.cucumber.core;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static lv.iljapavlovs.cucumber.constants.Constants.WAIT_EXPLICIT_SEC;
import static org.junit.Assert.fail;

public class WebElementHelper {

    private static final Logger logger = LoggerFactory.getLogger(WebElementHelper.class);

    public static boolean isElementDisplayed(WebElement webElement) {
        return isElementDisplayed(webElement, WAIT_EXPLICIT_SEC);
    }

    public static boolean isElementDisplayed(WebElement webElement, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), timeOut);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException ne) {
            return false;
        } catch (StaleElementReferenceException ex) {
            return isElementDisplayed(webElement, timeOut);
        }
    }

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), WAIT_EXPLICIT_SEC);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException te) {
            logger.error(te.getMessage());
            fail("Element '" + element + "' not found after waiting for it's visibility");
        } catch (NoSuchElementException ne) {
            logger.error(ne.getMessage());
            fail("Element '" + element + "' not found, unable to locate it in the DOM");
        } catch (Exception e) {
            logger.error(e.getMessage());
            fail("Element '" + element + "' not found");
        }
    }

    public static void sendKeys(WebElement webElement, CharSequence... keysToSend) {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), WAIT_EXPLICIT_SEC);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(keysToSend);
    }

    public static void click(WebElement webElement) {
        isElementDisplayed(webElement);
        waitForElementToBeClickable(webElement);
        webElement.click();
    }

    public static void click(By webElement) {
        waitForElementToBeClickable(webElement).click();
    }

    public static void selectByVisiableText(WebElement webElement, String value) {
        Select select = new Select(webElement);
        select.selectByVisibleText(value);
    }

    public static void waitForElementToBeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), WAIT_EXPLICIT_SEC);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));

    }

    public static WebElement waitForElementToBeClickable(By webElement) {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), WAIT_EXPLICIT_SEC);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static String getText(WebElement webElement) {
        waitForVisibility(webElement);
        return webElement.getText();
    }

    public static String getValue(WebElement webElement) {
        waitForVisibility(webElement);
        return webElement.getAttribute("value");
    }

}
