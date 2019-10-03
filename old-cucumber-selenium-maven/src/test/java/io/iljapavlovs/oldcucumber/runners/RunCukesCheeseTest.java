package io.iljapavlovs.oldcucumber.runners;

import io.cucumber.core.api.CucumberOptions;
import io.cucumber.core.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/cheese", "json:target/cucumber/cucumber-cheese.json"},
        features = {"src/test/resources/features"},
        tags = {"@cheese"},
        glue = {"io.iljapavlovs.oldcucumber.stepdefs", "io.iljapavlovs.oldcucumber.hooks"}
)
public class RunCukesCheeseTest {

}

