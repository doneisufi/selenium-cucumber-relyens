package assertions;

import pages.HomePage;
import static org.junit.Assert.assertTrue;

public class HomePageAssertions {

    private HomePage homePage;

    public HomePageAssertions(HomePage homePage) {
        this.homePage = homePage;
    }

    public void  assertLogoIsDisplayed() {
        assertTrue("The Relyens logo should be visible", homePage.logoIsDisplayed());
    }
}