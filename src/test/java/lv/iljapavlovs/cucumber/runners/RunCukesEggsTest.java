package lv.iljapavlovs.cucumber.runners;

import lv.iljapavlovs.cucumber.core.DriverBase;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/eggs", "json:target/cucumber/cucumber-eggs.json"},
        features = {"src/test/resources/features"},
        tags = {"@eggs"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs"}
)
public class RunCukesEggsTest {

}
