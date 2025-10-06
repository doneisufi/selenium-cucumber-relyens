package assertions;

import pages.RelyensPage;

import static org.junit.Assert.assertTrue;

public record RelyensPageAssertions(RelyensPage relyensPage) {

    public void assertTitleIsDisplayed() {
        assertTrue("The \" Relation clients \" title should be visible", relyensPage.isRelyensPageDisplayed());
    }

}