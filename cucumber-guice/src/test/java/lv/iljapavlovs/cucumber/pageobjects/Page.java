package lv.iljapavlovs.cucumber.pageobjects;

import static lv.iljapavlovs.cucumber.config.Constants.WAIT_SHORT_SECONDS;

import lv.iljapavlovs.cucumber.core.DriverBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page() {
        driver = DriverBase.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, WAIT_SHORT_SECONDS);
    }

}
