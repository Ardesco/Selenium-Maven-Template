package io.iljapavlovs.oldcucumber.pageobjects;

import io.iljapavlovs.oldcucumber.config.ApplicationProperties;
import io.iljapavlovs.oldcucumber.config.ApplicationProperties.ApplicationProperty;
import io.iljapavlovs.oldcucumber.core.DriverBase;
import io.iljapavlovs.oldcucumber.core.WebElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class Page {
    private final static int WAIT_SHORT_SECONDS =  ApplicationProperties.getInteger(ApplicationProperty.WAIT_SHORT_SECONDS);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page() {
        driver = DriverBase.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, WAIT_SHORT_SECONDS);
    }

    public void waitUntilLoaded() {
        WebElementHelper.waitForVisibility(getControlElement());
    }

    public boolean isPageDisplayed() {
        WebElement controlElement = getControlElement();
        return controlElement != null && WebElementHelper.isElementDisplayed(controlElement);
    }

    protected abstract WebElement getControlElement();

}
