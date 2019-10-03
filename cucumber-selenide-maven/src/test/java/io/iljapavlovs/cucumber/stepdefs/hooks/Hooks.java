package io.iljapavlovs.cucumber.stepdefs.hooks;


import static java.lang.String.format;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.iljapavlovs.cucumber.config.ApplicationProperties;
import io.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty;
import io.iljapavlovs.cucumber.utils.docker.ChromeContainer;
import io.iljapavlovs.cucumber.utils.docker.CurrentBrowserHolder;
import io.iljapavlovs.cucumber.utils.docker.DockerSupport;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class Hooks {

  private ChromeContainer chromeContainer;



  @Before
  public void setUpSelenium() throws IllegalAccessException, InstantiationException {

    String testEnvironmnetHost;
    if (ApplicationProperties.getBoolean(ApplicationProperty.USE_TESTCONTAINERS)) {
      chromeContainer = new ChromeContainer();
      chromeContainer.start();
      log.info("Testcontainers VNC address for UI tests: " + chromeContainer.getVncAddress());

      CurrentBrowserHolder.setBrowser(chromeContainer);
      RemoteWebDriver webDriver = chromeContainer.getWebDriver();

      final Capabilities capabilities = webDriver.getCapabilities();
      log.info("Browser Name: {}, Version: {}", capabilities.getBrowserName(), capabilities.getVersion());
      WebDriverRunner.setWebDriver(webDriver);
      testEnvironmnetHost = DockerSupport.currentHostIpInDocker();
    } else {
      testEnvironmnetHost = "localhost";
    }
//        dataHolder.setTestEnvironmentHost(testEnvironmnetHost);
//        String uiUrl = format("http://%s:%d", testEnvironmnetHost, environmentConfig.getUiPort());

    String uiUrl = ApplicationProperties.getString(ApplicationProperty.APP_URL);

    log.info("UI - Initializing Selenide setup");

    log.info("UI URL: " + uiUrl);
    Configuration.baseUrl = uiUrl;

    Configuration.timeout = 8000;//default is 4000
    Configuration.driverManagerEnabled = true;
    //some Selenide Configuration settings does not work when providing own driver
    Configuration.startMaximized = true;
//    todo - setting does not affects window size when providing own driver in testcontainers
//    Configuration.browserSize = "1920x1080";
    Configuration.screenshots = true;
  }

  @After
  public void tearDownSelenium(Scenario scenario) throws Exception {
    if (scenario.isFailed() && WebDriverRunner.hasWebDriverStarted()) {
      File screenshot = Screenshots.takeScreenShotAsFile();
      byte[] generatedScreenshot = null;
      try {
        generatedScreenshot = FileUtils.readFileToByteArray(screenshot);
        scenario.embed(generatedScreenshot, "image/png");

      } catch (IOException e) {
        log.warn(e.getMessage());
      }

      File file = new File(format("target/screenshots/%s.png", scenario.getName()));
      log.info("Screenshot taken at " + file.getAbsolutePath());
      FileUtils.writeByteArrayToFile(file, generatedScreenshot);
    }
    log.info("UI - Closing browser");

    if (ApplicationProperties.getBoolean(ApplicationProperty.USE_TESTCONTAINERS)) {
      chromeContainer.saveVideo(scenario);
      CurrentBrowserHolder.stop();
    } else {
      WebDriverRunner.getWebDriver().quit();
    }
  }

}
