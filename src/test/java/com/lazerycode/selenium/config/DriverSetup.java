package com.lazerycode.selenium.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverSetup {

    RemoteWebDriver getWebDriverObject(MutableCapabilities desiredCapabilities);

    MutableCapabilities getDesiredCapabilities(Proxy proxySettings);
}