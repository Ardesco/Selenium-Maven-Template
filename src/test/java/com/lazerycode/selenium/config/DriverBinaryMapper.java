package com.lazerycode.selenium.config;

import java.util.HashMap;
import java.util.Map;

import static com.lazerycode.selenium.config.DriverBinaryContext.binaryFor;
import static com.lazerycode.selenium.config.DriverBinaryContext.binaryPath;
import static com.lazerycode.selenium.config.DriverType.CHROME;
import static com.lazerycode.selenium.config.DriverType.IE;
import static com.lazerycode.selenium.config.OperatingSystem.*;
import static com.lazerycode.selenium.config.SystemArchitecture.ARCHITECTURE_32_BIT;
import static com.lazerycode.selenium.config.SystemArchitecture.ARCHITECTURE_64_BIT;

public class DriverBinaryMapper {
    private static final Map<DriverBinaryContext, String> binaryLocation = new HashMap<DriverBinaryContext, String>() {{
        put(binaryFor(CHROME, WINDOWS, ARCHITECTURE_64_BIT), binaryPath("/windows/googlechrome/64bit/2.10/chromedriver.exe"));
        put(binaryFor(CHROME, MAC, ARCHITECTURE_64_BIT), binaryPath("/osx/googlechrome/64bit/2.10/chromedriver"));
        put(binaryFor(CHROME, LINUX, ARCHITECTURE_64_BIT), binaryPath("/linux/googlechrome/64bit/2.10/chromedriver"));
        put(binaryFor(CHROME, WINDOWS, ARCHITECTURE_32_BIT), binaryPath("/windows/googlechrome/32bit/2.10/chromedriver.exe"));
        put(binaryFor(CHROME, MAC, ARCHITECTURE_32_BIT), binaryPath("/osx/googlechrome/32bit/2.10/chromedriver"));
        put(binaryFor(CHROME, LINUX, ARCHITECTURE_32_BIT), binaryPath("/linux/googlechrome/32bit/2.10/chromedriver"));
        put(binaryFor(DriverType.PHANTOMJS, WINDOWS, ARCHITECTURE_64_BIT), binaryPath("/windows/phantomjs/64bit/1.9.2/phantomjs.exe"));
        put(binaryFor(DriverType.PHANTOMJS, MAC, ARCHITECTURE_64_BIT), binaryPath("/osx/phantomjs/64bit/1.9.7/phantomjs"));
        put(binaryFor(DriverType.PHANTOMJS, LINUX, ARCHITECTURE_64_BIT), binaryPath("/linux/phantomjs/64bit/1.9.7/phantomjs"));
        put(binaryFor(DriverType.PHANTOMJS, WINDOWS, ARCHITECTURE_32_BIT), binaryPath("/windows/phantomjs/32bit/1.9.7/phantomjs.exe"));
        put(binaryFor(DriverType.PHANTOMJS, MAC, ARCHITECTURE_32_BIT), binaryPath("/osx/phantomjs/32bit/1.9.7/phantomjs"));
        put(binaryFor(DriverType.PHANTOMJS, LINUX, ARCHITECTURE_32_BIT), binaryPath("/linux/phantomjs/32bit/1.9.7/phantomjs"));
        put(binaryFor(IE, WINDOWS, ARCHITECTURE_64_BIT), binaryPath("/windows/internetexplorer/64bit/2.42.0/IEDriverServer.exe"));
        put(binaryFor(IE, WINDOWS, ARCHITECTURE_32_BIT), binaryPath("/windows/internetexplorer/32bit/2.42.0/IEDriverServer.exe"));

    }};

    static void configureBinary(DriverType driverType, OperatingSystem operatingSystem, SystemArchitecture systemArchitecture) {
        final String binarySystemProperty = driverType.getWebDriverSystemPropertyKey();

        if (null != binarySystemProperty && binarySystemProperty.length() != 0) {
            final String binaryConfiguration = binaryLocation.get(binaryFor(driverType, operatingSystem, systemArchitecture));
            System.out.println("Setting System Property '" + binarySystemProperty + "'='" + binaryConfiguration + "'");
            System.setProperty(binarySystemProperty, binaryConfiguration);
        }
    }

    static String getBinaryPath(DriverType browserType, OperatingSystem osName, SystemArchitecture systemArch) {
        return binaryLocation.get(binaryFor(browserType, osName, systemArch));
    }
}
