package pages;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import utils.ElementHighlighter;
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
        By navLink = By.xpath("//a[contains(normalize-space(text()), '" + linkText + "')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(navLink));
        utils.ElementHighlighter.highlight(driver, el);
        el.click();
    }

    // Simple method to click the page button with highlight (based on data-name and text content)
    public void clickPageButton(String pageName, String pageIdentifier) {
        By buttonLocator = By.xpath(
                "//div[@data-name='" + pageName + "']/div/div/button[contains(normalize-space(.), '" + pageIdentifier + "')]" +
                        " | " +
                        "//div[@data-name='" + pageName + "']/div/div/a[contains(normalize-space(.), '" + pageIdentifier + "')]"
        );
        By submenuContainer = By.cssSelector("div.header_submenu__inner");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(submenuContainer));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
        utils.ElementHighlighter.highlight(driver, button); // Highlight the button
        button.click();
    }

    // Click nav link and then click the button
    public void clickAndVerifyNavLink(String linkText, String pageName, String pageIdentifier) {
        clickNavigationLink(linkText);
        clickPageButton(pageName, pageIdentifier);
    }

    // Check presence of the first card with explicit wait
    public boolean isFirstCardDisplayed(String pageName) {
        try {
            // Retry loop to handle async rendering
            for (int i = 0; i < 20; i++) { // up to 10 seconds total
                // Build XPath dynamically with pageName
                String xpath = "(//div[@data-name='" + pageName + "']/div/div[contains(@class, 'header_submenu__depth2_wrapper')]/div/a)";

                List<WebElement> cards = driver.findElements(By.xpath(xpath));
                for (WebElement card : cards) {
                    if (card.isDisplayed()) {
                        utils.ElementHighlighter.highlight(driver, card);
                        return true; // found visible card
                    }
                }

                Thread.sleep(500); // wait before retrying
            }

            System.out.println("No visible first card found for page '" + pageName + "' after retries.");
            return false;

        } catch (Exception e) {
            System.out.println("First card not visible after wait: " + e.getMessage());
            return false;
        }
    }

}
