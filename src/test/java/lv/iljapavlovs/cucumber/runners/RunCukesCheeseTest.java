package lv.iljapavlovs.cucumber.runners;

import lv.iljapavlovs.cucumber.config.DriverBase;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/cheese", "json:target/cucumber/cucumber-cheese.json"},
        features = {"src/test/resources/features"},
        tags = {"@cheese"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs"}
)
public class RunCukesCheeseTest {

    @BeforeClass
    public static void boot() throws Exception {
        System.out.println("Starting RunCukesCheeseTest!");
        DriverBase.instantiateDriverObject();
    }

    @AfterClass
    public static void shutdown() throws Exception {
        System.out.println("Ending RunCukesCheeseTest!");
        DriverBase.closeDriverObjects();
    }
}

