package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import assertions.HomePageAssertions;

public class HomePageSteps {

    private static WebDriver driver;
    private HomePageAssertions homePageAssertions;

    @Given("I open the Relyens homepage")
    public void i_open_the_relyens_homepage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePageAssertions = new HomePageAssertions(homePage);

        homePage.open();
        homePage.acceptCookiesIfPresent(); // ✅ Handle cookies popup
    }

    @Then("I should see the Relyens logo")
    public void i_should_see_the_relyens_logo() {
        homePageAssertions.assertLogoIsDisplayed();
        //driver.quit();
    }
}
