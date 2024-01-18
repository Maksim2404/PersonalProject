package pages.cucumber.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

    @Given("User is on landing page")
    public void userIsOnLandingPage() {
    }

    @When("User logged into app with right credentials userName and password")
    public void userLoggedIntoAppWithRightCredentialsUserNameAndPassword() {

        System.out.println("Logged in success");
    }

    @Then("Home page is populated")
    public void homePageIsPopulated() {

        System.out.println("Home page is populated");
    }

    @And("Cards are displayed")
    public void cardsAreDisplayed() {

        System.out.println("Cards are displayed");
    }

    @When("User logged into app with right credentials userName {string} and password {string}")
    public void userLoggedIntoAppWithRightCredentialsUserNameAndPassword(String arg0, String arg1) {
        System.out.println(arg0);
        System.out.println(arg1);
    }
}
