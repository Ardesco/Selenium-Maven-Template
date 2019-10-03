package io.iljapavlovs.cucumber.utils;

import static com.codeborne.selenide.Condition.text;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import java.util.stream.Collectors;

public class SelenideHelper {
  public static void selectOption(SelenideElement dropdown, ElementsCollection options, String option) {
    dropdown.click();
    SelenideElement elementOption = options.find(text(option));
    elementOption.scrollTo();
    highlightElement(elementOption);
    elementOption.click();
  }

  public static List<String> getDropdownOptions(SelenideElement dropdown, ElementsCollection options){
    dropdown.click();
    return options.stream().map(SelenideElement::getText).collect(Collectors.toList());
  }

  public static void highlightElement(SelenideElement elementToHihghlight) {
    Selenide.executeJavaScript("arguments[0].setAttribute('style', 'background-color:coral')", elementToHihghlight);
  }


  /**
   * @return textContent is used instead of text() (getText() in Selenium) since in order to perform filtering, need to get text of the hidden element
   *
   * Main table consist of 2 tables -
   * 1. one with text elements (name, description, etc)
   * 2. other with buttons (edit, archive, etc)
   *
   * Both have duplicates - text element table have buttons, but these are hidden and vice versa - buttons table have text element duplicates, but those are hidden.
   */
  public static SelenideElement findRowByChildText(ElementsCollection elements, String childSelector, String childText) {
    return elements
        .find(CustomCondition.child(childSelector, Condition.attribute("textContent", childText)));
  }

  /**
   * in some cases standard element.clear() does not work on input fields
   * This is a workaround for that
   *
   * @param element on which perform 'value' attribute value clearing
   */
  public static void clearValue(SelenideElement element) {
    Selenide.executeJavaScript("arguments[0].value= '';", element);
  }
}
