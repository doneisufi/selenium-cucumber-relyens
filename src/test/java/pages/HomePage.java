package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // Locators
    private By logo = By.cssSelector("svg[data-icon-name='logo']");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void open() {
        driver.get("https://www.relyens.eu/fr/");
    }

    // Get validations
    public boolean logoIsDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }
}