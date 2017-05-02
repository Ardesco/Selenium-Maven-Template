package lv.iljapavlovs.cucumber.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class GoogleSearchResultPage{

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "g")
    private List<WebElement> searchResultElements;

    public GoogleSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("g")));
    }

    public List<WebElement> getSearchResultElements(){
        return searchResultElements;
    };
}
