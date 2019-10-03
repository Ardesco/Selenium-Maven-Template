package io.iljapavlovs.cucumber.utils.docker;


import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.lifecycle.TestDescription;


public class ChromeContainer extends BrowserWebDriverContainer implements TestBrowser {


  public ChromeContainer() {
    VncRecordingMode recordingMode = VncRecordingMode.SKIP;
    withCapabilities(chromeOptions())
        .withRecordingMode(recordingMode, new File("build"));
//        .withRecordingFileFactory(new CustomRecordingFileFactory());
  }

  private static ChromeOptions chromeOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setHeadless(true);

    Map<String, Object> perfLogPrefs = new HashMap<>();
//    perfLogPrefs.put("traceCategories", "browser,devtools.timeline,devtools");
//    perfLogPrefs.put("enableNetwork", true);
//    chromeOptions.setExperimentalOption("perfLoggingPrefs", perfLogPrefs);
    /**workaround since Selenium`s maximize() produces
     *'unknown error: failed to change window state to maximized, current state is normal'
     * https://github.com/SeleniumHQ/docker-selenium/issues/559
     */
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--no-sandbox");
//    chromeOptions.addArguments("--disable-setuid-sandbox");

//    chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    return chromeOptions;
  }

  public void saveVideo(Scenario scenario) {
    boolean isPassed = false;

    if (scenario.getStatus() == Status.PASSED) {
      isPassed = true;
    }

    afterTest(toDescription(scenario), Optional.of(isPassed));
  }

  public ChromeContainer getContainer() {
    return this;
  }

  private TestDescription toDescription(final Scenario scenario) {
    return new TestDescription() {
      public String getTestId() {
        return scenario.getId();
      }

      public String getFilesystemFriendlyName() {
        return scenario.getName().replaceAll("[^a-zA-Z0-9-_\\.]", "_");
      }
    };
  }

  @Override
  public void stop() {
    super.stop();
  }
}
