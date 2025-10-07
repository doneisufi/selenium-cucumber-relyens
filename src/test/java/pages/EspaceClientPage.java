package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementHighlighter;


public class EspaceClientPage {

    private final WebDriver driver;

    // Locators
    private final By espaceClientTitle =
            By.xpath("//div[contains(@class, 'panel') and contains(text(), 'Vous êtes')]");

    // Constructor
    public EspaceClientPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public boolean isEspaceClientPageDisplayed() {
        WebElement el = driver.findElement(espaceClientTitle);
        ElementHighlighter.highlightVerify(driver, el);
        return el.isDisplayed();
    }

}
