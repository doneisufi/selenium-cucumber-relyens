package assertions;

import pages.EspaceClientPage;
import static org.junit.Assert.assertTrue;


public record EspaceClientPageAssertions(EspaceClientPage espaceClientPage) {

    public void assertTitleIsDisplayed() {
        assertTrue("The \" ESPACE CLIENT \" title should be visible", espaceClientPage.isEspaceClientPageDisplayed());
    }

}