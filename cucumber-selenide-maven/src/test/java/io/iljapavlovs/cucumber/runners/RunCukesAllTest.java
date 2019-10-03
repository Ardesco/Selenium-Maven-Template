package io.iljapavlovs.cucumber.runners;




import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/cucumber-report", "json:target/cucumber/cucumber-report.json"},
        features = {"src/test/resources/features"},
        tags = {"@all"},
        glue = {"io.iljapavlovs.cucumber.stepdefs", "io.iljapavlovs.cucumber.hooks"}
)
public class RunCukesAllTest {
  private static long duration;

  @BeforeClass
  public static void before() {
    // todo - remove : BeforeClass is executed once in parallel testing as well
    duration = System.currentTimeMillis();
    System.out.println("BEFORE CLASS - Thread Id  | Scenario Num       | Step Count");
  }

  @AfterClass
  public static void after() {
    // todo - remove : AfterClass is executed once in parallel testing as well
    duration = System.currentTimeMillis() - duration;
    System.out.println("AFTER CLASS - DURATION - "+ duration);
  }
}
