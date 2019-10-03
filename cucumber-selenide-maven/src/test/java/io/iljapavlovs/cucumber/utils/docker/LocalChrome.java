package io.iljapavlovs.cucumber.utils.docker;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalChrome implements TestBrowser {

  private ChromeDriver chromeDriver;

  @Override
  public void start() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeDriver = new ChromeDriver(chromeOptions.merge(loggingPrefs()));
  }

  @Override
  public void stop() {
    chromeDriver.quit();
  }

  @Override
  public RemoteWebDriver getWebDriver() {
    return chromeDriver;
  }
}
