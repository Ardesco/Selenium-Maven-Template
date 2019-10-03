package io.iljapavlovs.cucumber.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/eggs", "json:target/cucumber/cucumber-eggs.json"},
        features = {"src/test/resources/features"},
        tags = {"@eggs"},
        glue = {"io.iljapavlovs.cucumber.stepdefs", "io.iljapavlovs.cucumber.hooks"}
)
public class RunCukesEggsTest {

}
