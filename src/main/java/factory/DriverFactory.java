package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger();

    public static Properties getProp() {
        return prop;
    }

    private static Properties prop;

    public OptionsManager optionsManager;

    public static void quitDriver() {
        getDriver().quit();
        driver.remove();
    }

    /*initialize the driver on the basis of browser
     * return driver*/
//    public WebDriver initDriver() {
    public WebDriver initDriver(Properties prop) {
        String browser = prop.getProperty("browser");
        logger.info("Browser name: {}", browser);
        optionsManager = new OptionsManager(prop);

        boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));

        switch (browser.trim().toUpperCase()) {
            case "CHROME":
                //run in remote
                if (remoteExecution) {
                    initRemoteDriver("CHROME");
                } else {
                    //run in local
                    driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                }
                break;
            case "EDGE":
                //run in remote
                if (remoteExecution) {
                    initRemoteDriver("EDGE");
                } else {
                    //run in local
                    driver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                }
                break;
            case "FIREFOX":
                //run in remote
                if (remoteExecution) {
                    initRemoteDriver("FIREFOX");
                } else {
                    //run in local
                    driver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                }
                break;
            case "SAFARI":
                //run in remote
                if (remoteExecution) {
                    initRemoteDriver("SAFARI");
                } else {
                    //run in local
                    driver.set(new SafariDriver(optionsManager.getSafariOptions()));
                }
                break;
            default:
                logger.error("Error while initializing driver...");
                throw new RuntimeException("====INVALID BROWSER====");
        }
        return getDriver();
    }

    public void initRemoteDriver(String browserName) {
        logger.info("Running tests on Selenium Grid for browser {}", browserName);
        try {
            switch (browserName.trim().toUpperCase()) {
                case "CHROME":
                    driver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), optionsManager.getChromeOptions()));
                    break;
                case "EDGE":
                    driver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), optionsManager.getEdgeOptions()));
                    break;
                case "FIREFOX":
                    driver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), optionsManager.getFirefoxOptions()));
                    break;
                case "SAFARI":
                    driver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), optionsManager.getSafariOptions()));
                    break;
                default:
                    logger.error("Plz supply the right browser name for selenium grid....");
                    throw new RuntimeException("====INVALID BROWSER====");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static Properties initProp() throws FileNotFoundException {
        prop = new Properties();
        FileInputStream ip = null;
        String envName = System.getProperty("env");
        try {
            if (envName == null) {
                logger.info("Env is null, so running tests in QA env by default...");
                ip = new FileInputStream("./src/test/resources/testData/TestdataQA.properties");
            } else {
                switch (envName.trim().toUpperCase()) {
                    case "DEV":
                        ip = new FileInputStream(("./src/test/resources/testData/TestdataDEV.properties"));
                        break;
                    case "QA":
                        ip = new FileInputStream("./src/test/resources/testData/TestdataQA.properties");
                        break;
                    case "STG":
                        ip = new FileInputStream("./src/test/resources/testData/TestdataSTG.properties");
                        break;
                    default:
                        logger.error("Env value is invalid...plz pass the right env value..");
                        throw new RuntimeException("====INVALID ENVIRONMENT====");
                }
            }
        } catch (FileNotFoundException | RuntimeException e) {
            throw new RuntimeException(e);
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    /*Screenshot*/
    public static File getScreenshotFile() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    }

    public static byte[] getScreenshotByte() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String getScreenshotBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
