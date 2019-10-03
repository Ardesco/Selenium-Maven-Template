package lv.iljapavlovs.cucumber.core;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverSetup {

    RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);
}
