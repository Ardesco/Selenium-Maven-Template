package io.iljapavlovs.cucumber.utils;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomCondition {

  public static Condition child(final String childCssSelector, final Condition condition) {
    return new Condition("child " + childCssSelector + " has " + condition.toString()) {

      @Override
      public boolean apply(Driver driver, WebElement element) {
        SelenideElement child = $(element.findElement(By.cssSelector(childCssSelector)));
        return child.has(condition);
      }
    };
  }


}
