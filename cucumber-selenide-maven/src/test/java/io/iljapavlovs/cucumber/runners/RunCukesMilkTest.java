package io.iljapavlovs.cucumber.runners;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/milk", "json:target/cucumber/cucumber-milk.json"},
        features = {"src/test/resources/features"},
        tags = {"@milk"},
        glue = {"lv.iljapavlovs.cucumber.stepdefs", "lv.iljapavlovs.cucumber.hooks"}
)
public class RunCukesMilkTest {

}
