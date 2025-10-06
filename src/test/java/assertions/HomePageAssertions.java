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

    public void assertFirstCardIsDisplayed(String pageName) {
        assertTrue("The first card should be visible on the homepage for page: " + pageName,
                homePage.isFirstCardDisplayed(pageName));
    }

}