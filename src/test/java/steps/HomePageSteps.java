package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import assertions.HomePageAssertions;

public class HomePageSteps {

    private static WebDriver driver;
    private static HomePage homePage;
    private static HomePageAssertions homePageAssertions;

    @Given("I open the Relyens homepage")
    public void i_open_the_relyens_homepage() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        if (homePage == null) {
            homePage = new HomePage(driver);
            homePageAssertions = new HomePageAssertions(homePage);
        }

        homePage.open();
        homePage.acceptCookiesIfPresent(); // Handle cookies popup
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
        homePage.clickFirstNavLink();
    }

    // Tear down after all steps
    @And("I close the browser")
    public void i_close_the_browser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            homePage = null;
            homePageAssertions = null;
        }
    }
}
