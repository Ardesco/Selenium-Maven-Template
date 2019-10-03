package io.iljapavlovs.oldcucumber.hooks;


import io.cucumber.core.api.Scenario;
import io.cucumber.core.api.java.After;
import io.cucumber.core.api.java.Before;
import io.iljapavlovs.oldcucumber.core.DriverBase;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.MDC;

@Slf4j
public class Hooks {

    @Before
    public void setup(Scenario scenario) throws Exception {
        MDC.put("scenarioId", "scenarioId:" + UUID.randomUUID().toString());
        String sessionId = ((RemoteWebDriver) DriverBase.getDriver()).getSessionId().toString();
        log.info("Starting Scenario: \"" + scenario.getName() + "\" with Session ID: " + sessionId);
        DriverBase.getDriver().manage().deleteAllCookies();
//        sometimes fails if running in Selenium Grid
//        DriverBase.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverBase.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException | ClassCastException wde) {
                log.error(wde.getMessage());
            }
        }
        log.info(String.format("Ending Scenario: \"%s\"", scenario.getName()) + " result: " + (scenario.isFailed() ? "FAILED" : "PASSED"));
        DriverBase.closeDriverObjects();
    }
}
