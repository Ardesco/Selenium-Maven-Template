package com.lazerycode.selenium.pages.components;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import com.lazerycode.selenium.pages.HomePage;
import com.lazerycode.selenium.pages.internal.PageFactory;

public class Header extends HtmlElement {

	@Name("Rightmove logo")
	@FindBy(css = "#sitelogo a")
	public Button logo;

	@Step("Click Rightmove logo to return to the home page")
	public HomePage clickLogoToGoHome() {
		logo.click();
		return PageFactory.getInstance(HomePage.class);
	}
}
