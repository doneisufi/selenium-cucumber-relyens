package assertions;

import pages.ProfessionnelSantePage;

import static org.junit.Assert.assertTrue;


public record ProfessionnelSantePageAssertions(ProfessionnelSantePage professionnelSantePage) {

    public void assertTitleIsDisplayed() {
        assertTrue("The \" Professionnel de santé \" title should be visible", professionnelSantePage.isEspaceClientPageDisplayed());
    }

    public void assertErrorMsgIsDisplayed() {
        assertTrue("The error message should be visible", professionnelSantePage.isErrorMessagePresent());
    }

}