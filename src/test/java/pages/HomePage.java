package pages;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.*;
import utils.ElementHighlighter;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    private final WebDriver driver;

    // Locators
    private final By logo = By.xpath("//h1/a[@aria-label='Relyens']");
    private final By acceptCookiesBtn = By.id("tarteaucitronPersonalize2");
    private final By assuranceText = By.xpath("//div[contains(text(), 'ASSURANCE ET MANAGEMENT DES RISQUES')]");
    private final By espaceClientButton = By.xpath("//button[contains(@class, 'customer') and contains(@class, 'secondary')]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Open homepage
    public void open() {
        driver.get("https://www.relyens.eu/fr/");
    }

    // Accept cookies if present
    public void acceptCookiesIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            ElementHighlighter.highlightClick(driver, button);
            ElementHighlighter.highlightClick(driver, button);
            button.click();
            System.out.println("Cookies popup accepted.");
        } catch (Exception e) {
            System.out.println("No cookies popup found.");
        }
    }

    // Dismiss welcome message popup if it appears
    public void dismissWelcomeMessage() {
        try {
            // Wait briefly to allow iframe to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

            // Switch to the iframe by ID
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("widget-Botsonic"));

            // Locate the close button inside the iframe
            By welcomeBtn = By.xpath("//div[@class='welcome-message cursor-pointer']/div/button");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(welcomeBtn));

            // Highlight and click
            ElementHighlighter.highlightClick(driver, btn);
            btn.click();

            System.out.println("Welcome message dismissed inside iframe.");

            // Short pause to ensure action is processed
            Thread.sleep(300);

        } catch (Exception e) {
            System.out.println("No welcome message iframe found or already dismissed: " + e.getMessage());
        } finally {
            // Switch back to main content
            driver.switchTo().defaultContent();
        }
    }

    // Get validations
    public boolean logoIsDisplayed() {
        WebElement el = driver.findElement(logo);
        ElementHighlighter.highlightVerify(driver, el); // highlight before verifying
        return el.isDisplayed();
    }

    // Check if the text is displayed
    public boolean isAssuranceTextDisplayed() {
        WebElement el = driver.findElement(assuranceText);
        ElementHighlighter.highlightVerify(driver, el);
        return el.isDisplayed();
    }

    // Click navigation link by visible text
    public void clickNavigationLink(String linkText) {
        By navLink = By.xpath("//a[contains(normalize-space(text()), '" + linkText + "')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(navLink));
        ElementHighlighter.highlightClick(driver, el);
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
        ElementHighlighter.highlightClick(driver, button); // Highlight the button
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
                        ElementHighlighter.highlightVerify(driver, card);
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

    // Click ESPACE CLIENT button
    public void clickEspaceClientButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(espaceClientButton));
        ElementHighlighter.highlightClick(driver, el);
        el.click();
    }

}
