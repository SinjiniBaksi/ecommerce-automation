package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class SetupAndTearDown {

    @Before
    public void setup() {
        System.out.println("Setup");
    }

    @After
    public void tearDown() {
        System.out.println("Teardown");
    }
}
