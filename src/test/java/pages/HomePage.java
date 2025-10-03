package pages;

import org.openqa.selenium.By;
import utils.ElementHighlighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    // Locators
    private final By logo = By.xpath("//h1/a[@aria-label='Relyens']");
    private final By acceptCookiesBtn = By.id("tarteaucitronPersonalize2");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void open() {
        driver.get("https://www.relyens.eu/fr/");
    }

    public void acceptCookiesIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            ElementHighlighter.highlight(driver, button);
            button.click();
            System.out.println("✅ Cookies popup accepted.");
        } catch (Exception e) {
            System.out.println("ℹ️ No cookies popup found.");
        }
    }

    // Get validations
    public boolean logoIsDisplayed() {
        WebElement el = driver.findElement(logo);
        ElementHighlighter.highlight(driver, el); // highlight before verifying
        return el.isDisplayed();
    }

}
