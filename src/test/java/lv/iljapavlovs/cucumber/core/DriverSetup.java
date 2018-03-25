package lv.iljapavlovs.cucumber.core;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public interface DriverSetup {

    WebDriver getWebDriverObject(MutableCapabilities desiredCapabilities);

    MutableCapabilities getDesiredCapabilities(Proxy proxySettings);
}