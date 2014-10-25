package com.lazerycode.selenium.config;

import static com.lazerycode.selenium.DriverFactory.getDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.AbstractModule;

public class WebDriverModule extends AbstractModule {
	@Override
	protected void configure() {
		WebDriver driver = getDriver();
		bind(WebDriver.class).toInstance(driver);
		bind(WebDriverWait.class).toInstance(new WebDriverWait(driver, 10));
	}

}
