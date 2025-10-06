package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import hooks.Hooks;
import pages.HomePage;
import assertions.HomePageAssertions;

public class HomePageSteps {

    private WebDriver driver;
    private HomePage homePage;
    private HomePageAssertions homePageAssertions;

    @Given("I open the Relyens homepage")
    public void i_open_the_relyens_homepage() {
        // Fetch driver only when step runs (after @Before hook)
        driver = Hooks.driver;

        if (driver == null) {
            throw new IllegalStateException("WebDriver was not initialized. Check Hooks.java @Before method.");
        }

        homePage = new HomePage(driver);
        homePageAssertions = new HomePageAssertions(homePage);

        homePage.open();
        homePage.acceptCookiesIfPresent();
    }

    @Then("I should see the Relyens logo")
    public void i_should_see_the_relyens_logo() {
        homePageAssertions.assertLogoIsDisplayed();
    }

    @And("I should see the Assurance and Risk Management text")
    public void i_should_see_assurance_text() {
        homePageAssertions.assertAssuranceTextIsDisplayed();
    }

    @And("I click on the {string} navigation link")
    public void i_click_navigation_link(String linkText) {
        homePage.clickNavigationLink("Vous êtes");
        homePage.clickNavigationLink("Vos besoins");
        homePage.clickNavigationLink("Relyens");
    }


}
