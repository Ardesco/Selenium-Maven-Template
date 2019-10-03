package io.iljapavlovs.cucumber.utils.docker;

import java.util.logging.Level;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface TestBrowser {

  void start();

  void stop();

  RemoteWebDriver getWebDriver();

  default Capabilities loggingPrefs() {
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.BROWSER, Level.ALL);
    logPrefs.enable(LogType.CLIENT, Level.ALL);
//        logPrefs.enable(LogType.PROFILER, Level.ALL);
    logPrefs.enable(LogType.SERVER, Level.ALL);
    logPrefs.enable(LogType.DRIVER, Level.ALL);
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    return desiredCapabilities;
  }

}
