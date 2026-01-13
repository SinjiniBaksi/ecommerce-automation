package stepDefs.hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileNotFoundException;
import java.time.Duration;

public class SetupAndTearDown {

    @BeforeAll
    public static void beforeAll() throws FileNotFoundException {
        System.out.println("Before All - SetupAndTearDown");
        DriverFactory.initProp();
        // You can initialize properties or other global settings here if needed
    }

    @Before
    public void setup() {
        System.out.println("Setup");
        DriverFactory df = new DriverFactory();
        df.initDriver(DriverFactory.getProp());
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        DriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Teardown");
        System.out.println("Scenario status: " + scenario.getStatus());
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        DriverFactory.quitDriver();
    }
}
