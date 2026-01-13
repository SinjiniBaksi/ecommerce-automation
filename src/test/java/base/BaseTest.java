package base;

import factory.DriverFactory;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;

public class BaseTest {

    @BeforeSuite
    public void beforeSuite() throws FileNotFoundException {
        System.out.println("Before Suite - BaseTest");
        //DriverFactory.initProp();
    }
}
