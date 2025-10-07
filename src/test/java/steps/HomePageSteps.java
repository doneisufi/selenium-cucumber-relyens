package steps;

import hooks.Hooks;
import pages.HomePage;
import pages.RelyensPage;
import io.cucumber.java.en.*;
import assertions.HomePageAssertions;
import org.openqa.selenium.WebDriver;
import assertions.RelyensPageAssertions;

public class HomePageSteps {

    private WebDriver driver;
    private HomePage homePage;
    private RelyensPage relyensPage;
    private HomePageAssertions homePageAssertions;
    private RelyensPageAssertions relyensPageAssertions;

    @Given("I open the Relyens homepage")
    public void i_open_the_relyens_homepage() {
        // Fetch driver only when step runs (after @Before hook)
        driver = Hooks.driver;

        if (driver == null) {
            throw new IllegalStateException("WebDriver was not initialized. Check Hooks.java @Before method.");
        }

        // Pages
        homePage = new HomePage(driver);
        relyensPage = new RelyensPage(driver);

        // Assertions
        homePageAssertions = new HomePageAssertions(homePage);
        relyensPageAssertions = new RelyensPageAssertions(relyensPage);

        // Steps
        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.dismissWelcomeMessage();
    }

    @Then("I should see the Relyens logo")
    public void i_should_see_the_relyens_logo() {
        homePageAssertions.assertLogoIsDisplayed();
    }

    @And("I should see the Assurance and Risk Management text")
    public void i_should_see_assurance_text() {
        homePageAssertions.assertAssuranceTextIsDisplayed();
    }

    @And("I click on different navigation links and verify their first content")
    public void i_click_link_and_verify_page() throws InterruptedException {

        // Define each nav link with parameters in a simple array
        String[][] navLinks = {
                {"Vous êtes", "vous-etes", "Acteurs des territoires", "Acteurs du soin"},
                {"Vos besoins", "vos-besoins", "Acteurs du soin", "Acteurs des territoires"},
                {"Relyens", "relyens", "Entreprise responsable", "Le Groupe"}
        };

        for (String[] nav : navLinks) {
            String linkText = nav[0];
            String pageName = nav[1];
            String pageIdentifier = nav[2];
            String buttonToClick = nav[3];

            // Click navigation link and verify page content
            homePage.clickAndVerifyNavLink(linkText, pageName, pageIdentifier);
            // To be improved
            Thread.sleep(500);
            homePageAssertions.assertFirstCardIsDisplayed(pageName);

            // Click button and re-verify first card
            homePage.clickPageButton(pageName, buttonToClick);
            // To be improved
            Thread.sleep(500);
            homePageAssertions.assertFirstCardIsDisplayed(pageName);
        }
    }

    @And("I click La relation clients button in Relyens page then verify it's title")
    public void i_should_see_relation_clients_title() {
        homePage.clickPageButton("relyens","La relation clients");
        relyensPageAssertions.assertTitleIsDisplayed();
    }

}
