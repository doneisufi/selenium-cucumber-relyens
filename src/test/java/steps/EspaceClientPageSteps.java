package steps;

import hooks.Hooks;
import pages.HomePage;
import io.cucumber.java.en.*;
import pages.EspaceClientPage;
import pages.ProfessionnelSantePage;
import assertions.HomePageAssertions;
import org.openqa.selenium.WebDriver;
import assertions.EspaceClientPageAssertions;
import assertions.ProfessionnelSantePageAssertions;

public class EspaceClientPageSteps {

    private HomePage homePage;
    private EspaceClientPage espaceClientPage;
    private HomePageAssertions homePageAssertions;
    private ProfessionnelSantePage professionnelSantePage;
    private EspaceClientPageAssertions espaceClientPageAssertions;
    private ProfessionnelSantePageAssertions professionnelSantePageAssertions;

    @Given("I open the Relyens homepage for the second time")
    public void i_open_the_relyens_homepage() {

        // Initialize driver from Hooks
        WebDriver driver = Hooks.driver;
        if (driver == null) {
            throw new IllegalStateException("WebDriver was not initialized. Check Hooks.java @Before method.");
        }

        // Initialize pages and assertions
        homePage = new HomePage(driver);
        espaceClientPage = new EspaceClientPage(driver);
        professionnelSantePage = new ProfessionnelSantePage(driver);
        espaceClientPageAssertions = new EspaceClientPageAssertions(espaceClientPage);
        professionnelSantePageAssertions = new ProfessionnelSantePageAssertions(professionnelSantePage);

        // Steps
        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.dismissWelcomeMessage();
    }

    @And("I click Espace client button")
    public void i_should_see_espace_client_sidepage() {
        homePage.clickEspaceClientButton();
        espaceClientPageAssertions.assertTitleIsDisplayed();
    }

    @And("I open the Professionnel de santé page")
    public void i_open_the_professionnel_de_sante_page() {
        espaceClientPage.clickProfessionnelSanteLink();
        homePage.acceptCookiesIfPresent();
    }

    @Then("I should see the Professionnel de santé title")
    public void i_should_see_the_professionnel_de_sante_title() {
        professionnelSantePageAssertions.assertTitleIsDisplayed();
    }

    @When("I enter invalid credentials and try to login")
    public void i_enter_invalid_credentials() throws InterruptedException {
        boolean checkboxClicked = professionnelSantePage.checkAndClickVerificationCheckbox();
        if (checkboxClicked) {
            System.out.println("Human verification checkbox was present and clicked.");
        } else {
            System.out.println("Human verification checkbox was not present.");
        }
        // To be improved
        Thread.sleep(70000);
        professionnelSantePage.login("lisufi@cbtw.tech", "invalidPassword");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        professionnelSantePageAssertions.assertErrorMsgIsDisplayed();
    }

}
