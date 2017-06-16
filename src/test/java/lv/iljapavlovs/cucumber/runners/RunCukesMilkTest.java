package lv.iljapavlovs.cucumber.runners;

import lv.iljapavlovs.cucumber.core.DriverBase;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/milk", "json:target/cucumber/cucumber-milk.json"},
        features = {"src/test/resources/features"},
        tags = {"@milk"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs"}
)
public class RunCukesMilkTest {

}
