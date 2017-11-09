package com.lazerycode.selenium.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverSetup {

    WebDriver getWebDriverObject(MutableCapabilities desiredCapabilities);

    MutableCapabilities getDesiredCapabilities(Proxy proxySettings);
}