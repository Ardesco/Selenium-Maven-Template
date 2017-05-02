package lv.iljapavlovs.cucumber.runners;

import lv.iljapavlovs.cucumber.config.DriverBase;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber/eggs", "json:target/cucumber/cucumber-eggs.json"},
        features = {"src/test/resources/features"},
        tags = {"@eggs"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs"}
)
public class RunCukesEggsTest {

    @BeforeClass
    public static void boot() throws Exception {
        System.out.println("Starting RunCukesEggsTest!");
        DriverBase.instantiateDriverObject();
    }

    @AfterClass
    public static void shutdown() throws Exception {
        System.out.println("Ending RunCukesEggsTest!");
        DriverBase.closeDriverObjects();
    }
}
