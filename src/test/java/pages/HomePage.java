package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import utils.ElementHighlighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    private final WebDriver driver;

    // Locators
    private final By logo = By.xpath("//h1/a[@aria-label='Relyens']");
    private final By acceptCookiesBtn = By.id("tarteaucitronPersonalize2");
    private final By assuranceText = By.xpath("//div[contains(text(), 'ASSURANCE ET MANAGEMENT DES RISQUES')]");
    private final By firstNavLink = By.xpath("//a[contains(text(), 'Vous êtes')]");


    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void open() {
        driver.get("https://www.relyens.eu/fr/");
    }

    // Accept cookies
    public void acceptCookiesIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            ElementHighlighter.highlight(driver, button);
            button.click();
            System.out.println("Cookies popup accepted.");
        } catch (Exception e) {
            System.out.println("No cookies popup found.");
        }
    }

    // Get validations
    public boolean logoIsDisplayed() {
        WebElement el = driver.findElement(logo);
        ElementHighlighter.highlight(driver, el); // highlight before verifying
        return el.isDisplayed();
    }

    // Check if the text is displayed
    public boolean isAssuranceTextDisplayed() {
        WebElement el = driver.findElement(assuranceText);
        utils.ElementHighlighter.highlight(driver, el);
        return el.isDisplayed();
    }

    // Click navigation link by visible text
    public void clickNavigationLink(String linkText) {
        By navLink = By.xpath("//a[contains(text(), '" + linkText + "')]");

        WebElement el = driver.findElement(navLink);
        utils.ElementHighlighter.highlight(driver, el); // highlight before click
        el.click();

        System.out.println("Clicked navigation link: " + linkText);
    }

}
