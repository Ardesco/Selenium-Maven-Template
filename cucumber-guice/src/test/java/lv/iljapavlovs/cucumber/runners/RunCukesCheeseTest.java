package lv.iljapavlovs.cucumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/cheese", "json:target/cucumber/cucumber-cheese.json"},
        features = {"src/test/resources/features"},
        tags = {"@cheese"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs", "lv.iljapavlovs.cucumber.hooks"}
)
public class RunCukesCheeseTest {

}

