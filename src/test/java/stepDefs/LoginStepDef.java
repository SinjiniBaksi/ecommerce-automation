package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

    @Given("the application is launched")
    public void the_application_is_launched() {

    }

    @When("user is on login page and enters credentials {string} and {string}")
    public void user_is_on_login_page_and_enters_credentials_and(String string, String string2) {

    }

    @Then("the user is redirected to the homepage")
    public void the_user_is_redirected_to_the_homepage() {

    }

    @Then("the user is shown an error message {string}")
    public void the_user_is_shown_an_error_message(String string) {

    }
}
