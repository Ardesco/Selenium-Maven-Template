package com.lazerycode.selenium.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import com.lazerycode.selenium.config.utilities.IdeDriverUtils;

/**
 * <p>Date:    26/08/18
 *
 * @author mengxin
 * @version 1.0
 */
public class IdeDriverUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(IdeDriverUtilsTest.class);

    @Test
    public void testDriverPathString() {
        LOG.info(IdeDriverUtils.LINUX_GOOGLE_PATH);
        assert IdeDriverUtils.LINUX_GOOGLE_PATH.equals("/selenium_standalone_binaries/linux/googlechrome/64bit/chromedriver");
        assert IdeDriverUtils.LINUX_GECKO_PATH.equals("/selenium_standalone_binaries/linux/marionette/64bit/geckodriver");
        assert IdeDriverUtils.LINUX_OPERA_PATH.equals("/selenium_standalone_binaries/linux/operachromium/64bit/operadriver");
        assert IdeDriverUtils.LINUX_PHANTOMJS_PATH.equals("/selenium_standalone_binaries/linux/phantomjs/64bit/phantomjs");
        assert IdeDriverUtils.MACOS_GOOGLE_PATH.equals("/selenium_standalone_binaries/osx/googlechrome/64bit/chromedriver");
        assert IdeDriverUtils.MACOS_GECKO_PATH.equals("/selenium_standalone_binaries/osx/marionette/64bit/geckodriver");
        assert IdeDriverUtils.MACOS_OPERA_PATH.equals("/selenium_standalone_binaries/osx/operachromium/64bit/operadriver");
        assert IdeDriverUtils.MACOS_PHANTOMJS_PATH.equals("/selenium_standalone_binaries/osx/phantomjs/64bit/phantomjs");
        assert IdeDriverUtils.WIN_GOOGLE_PATH.equals("/selenium_standalone_binaries/windows/googlechrome/64bit/chromedriver.exe");
        assert IdeDriverUtils.WIN_GECKO_PATH.equals("/selenium_standalone_binaries/windows/marionette/64bit/geckodriver.exe");
        assert IdeDriverUtils.WIN_OPERA_PATH.equals("/selenium_standalone_binaries/windows/operachromium/64bit/operadriver.exe");
        assert IdeDriverUtils.WIN_PHANTOMJS_PATH.equals("/selenium_standalone_binaries/windows/phantomjs/64bit/phantomjs.exe");
        assert IdeDriverUtils.WIN_EDGE_PATH.equals("/selenium_standalone_binaries/windows/edge/64bit/MicrosoftWebDriver.exe");
        assert IdeDriverUtils.WIN_IE_PATH.equals("/selenium_standalone_binaries/windows/internetexplorer/64bit/IEDriverServer.exe");
    }


    @Test
    public void testIdeInitialDrive() {
        assert !IdeDriverUtils.ideInitialDrive();
        System.setProperty("ide", "1");
        assert IdeDriverUtils.ideInitialDrive();
        // LOG.info(System.getProperties().toString());

        LOG.info(System.getProperty("webdriver.chrome.driver"));
        LOG.info(System.getProperty("webdriver.gecko.driver"));
        LOG.info(System.getProperty("webdriver.opera.driver"));
        LOG.info(System.getProperty("webdriver.edge.driver"));
        LOG.info(System.getProperty("webdriver.ie.driver"));
    }
}