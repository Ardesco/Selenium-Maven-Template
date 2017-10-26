package lv.iljapavlovs.cucumber.hooks;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lv.iljapavlovs.cucumber.core.DriverBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) throws Exception {
        DriverBase.instantiateDriverObject();
        String sessionId = ((RemoteWebDriver) DriverBase.getDriver()).getSessionId().toString();
        logger.info("Starting Scenario: \"" + scenario.getName() + "\" with Session ID: " + sessionId);
        DriverBase.getDriver().manage().deleteAllCookies();
        DriverBase.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverBase.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                logger.error(wde.getMessage());
            } catch (ClassCastException cce) {
                logger.error(cce.getMessage());
            }
        }
        String sessionId = ((RemoteWebDriver)DriverBase.getDriver()).getSessionId().toString();
        logger.info("Ending Scenario: \"" + scenario.getName() + "\" with Session ID: " + sessionId);
        DriverBase.closeDriverObjects();
    }
}
