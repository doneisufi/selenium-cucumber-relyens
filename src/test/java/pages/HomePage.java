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


    // Verify that the expected section button is visible (based on data-target)
    public boolean isPageButtonVisible(String pageIdentifier) {
        By buttonLocator = By.xpath("//button[@data-target=\"" + pageIdentifier + "\"]");
        System.out.println("Checking button with locator: " + buttonLocator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // Step 1: wait until element exists in the DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(buttonLocator));

            // Step 2: wait until element is visible
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLocator));

            utils.ElementHighlighter.highlight(driver, button);
            System.out.println("Verified page button with data-target='" + pageIdentifier + "' is visible.");
            return true;
        } catch (Exception e) {
            System.out.println("Page button with data-target='" + pageIdentifier + "' was NOT found or not visible.");
            return false;
        }
    }

    // Click & verify pages
    public void clickAndVerifyNavLink(String linkText, String pageIdentifier) {
        clickNavigationLink(linkText);

        // Small wait to allow DOM update
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        if (!isPageButtonVisible(pageIdentifier)) {
            throw new AssertionError("Expected page button not visible after clicking link: " + linkText);
        }
    }


}
