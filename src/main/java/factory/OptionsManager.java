package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;

    private static final Logger logger = LogManager.getLogger();

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            logger.info("Running tests in headless mode in Chrome...");
            chromeOptions.addArguments("--headless");
        }
        return chromeOptions;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            logger.info("Running tests in headless mode in Edge...");
            edgeOptions.addArguments("--headless");
        }
        return edgeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            logger.info("Running tests in headless mode in FF...");
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }

    public SafariOptions getSafariOptions() {
        SafariOptions safariOptions = new SafariOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            logger.info("Running tests in headless mode in Safari...");
            safariOptions.setCapability("platformName","macOS");
        }
        return safariOptions;
    }
}
