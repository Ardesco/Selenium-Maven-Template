package com.lazerycode.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import com.google.inject.Inject;
import com.lazerycode.selenium.pages.internal.BasePage;
import com.lazerycode.selenium.pages.internal.PageFactory;

public class ForSaleFilterPage extends BasePage<ForSaleFilterPage> {

	@Inject
	private WebDriver driver;

	@Inject
	private WebDriverWait wait;
	
	@Name("Find Properties button")
	@FindBy(id = "submit")
	private Button findPropertiesButton;

	public ForSaleFilterPage get() {
		HtmlElementLoader.populatePageObject(this, driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id("submit")));
		return this;
	}

	@Step("Click Find Properties.")
	public ForSaleResultsPage findProperties() {
		findPropertiesButton.click();
		return PageFactory.getInstance(ForSaleResultsPage.class);
	}
}
