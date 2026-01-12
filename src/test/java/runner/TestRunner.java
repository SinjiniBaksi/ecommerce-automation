package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefs",
        tags = "@smoke",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

