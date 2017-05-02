package lv.iljapavlovs.cucumber.runners;

import lv.iljapavlovs.cucumber.config.DriverBase;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber/milk", "json:target/cucumber/cucumber-milk.json"},
        features = {"src/test/resources/features"},
        tags = {"@milk"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs"}
)
public class RunCukesMilkTest {

    @BeforeClass
    public static void boot() throws Exception {
        System.out.println("Starting RunCukesMilkTest!");
        DriverBase.instantiateDriverObject();
    }

    @AfterClass
    public static void shutdown() throws Exception {
        System.out.println("Ending RunCukesMilkTest!");
        DriverBase.closeDriverObjects();
    }
}
