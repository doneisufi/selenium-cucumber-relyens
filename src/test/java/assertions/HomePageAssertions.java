package assertions;

import pages.HomePage;
import static org.junit.Assert.assertTrue;

public record HomePageAssertions(HomePage homePage) {

    public void assertLogoIsDisplayed() {
        assertTrue("The Relyens logo should be visible", homePage.logoIsDisplayed());
    }

    public void assertAssuranceTextIsDisplayed() {
        assertTrue("The assurance text should be visible on the homepage",
                homePage.isAssuranceTextDisplayed());
    }

}