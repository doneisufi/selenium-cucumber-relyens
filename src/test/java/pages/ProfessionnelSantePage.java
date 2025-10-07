package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementHighlighter;
import utils.ElementWaiter;
import java.time.Duration;


public class ProfessionnelSantePage {

    private final WebDriver driver;

    // Locators
    private final By professionnelSanteTitle = By.xpath("//h1[contains(@class, 'title') and contains(text(), 'Votre espace client assurance responsabilité civile et dommage aux biens')]");
    private final By usernameInput = By.xpath("//input[@type='email']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[@type='submit']");

    // Constructor
    public ProfessionnelSantePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public boolean isEspaceClientPageDisplayed() {
        WebElement el = driver.findElement(professionnelSanteTitle);
        ElementHighlighter.highlightVerify(driver, el);
        return el.isDisplayed();
    }

    // Logs in using the provided username and password
    public void login(String username, String password) {
        WebElement usernameField = ElementWaiter.visible(driver, usernameInput);
        WebElement passwordField = ElementWaiter.visible(driver, passwordInput);
        WebElement loginBtn = ElementWaiter.clickable(driver, loginButton);

        ElementHighlighter.highlightClick(driver, loginBtn);

        usernameField.clear();
        passwordField.clear();

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        loginBtn.click();
    }

    public boolean isErrorMessagePresent() {
        try {
            WebElement errorMessage = driver.findElement(By.xpath(
                    "//rds-notification[@variant='error' and contains(@title, 'Vos accès sont inconnus ou invalides.')]"
            ));
            return errorMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean checkAndClickVerificationCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the specific iframe with class="frc-i-widget"
        By iframeLocator = By.cssSelector("iframe.frc-i-widget");
        try {
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
            System.out.println("Found iframe with class 'frc-i-widget'. Switching to it...");

            // Switch to the iframe
            driver.switchTo().frame(iframe);

            // Find the checkbox inside the iframe
            By checkboxLocator = By.xpath("//button[@role='checkbox' and @aria-checked='false']");
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxLocator));

            if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                System.out.println("Verification checkbox found in iframe, attempting to click.");
                checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));
                try {
                    checkbox.click();
                    System.out.println("Verification checkbox clicked successfully.");
                } catch (Exception e) {
                    System.out.println("Regular click on checkbox failed: " + e.getMessage());
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", checkbox);
                    System.out.println("Verification checkbox clicked via JavaScript.");
                }

                // Switch back to the main content
                driver.switchTo().defaultContent();
                return true;
            } else {
                System.out.println("Checkbox found but not displayed or enabled.");
                driver.switchTo().defaultContent();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to find or interact with iframe/checkbox: " + e.getMessage());
            driver.switchTo().defaultContent(); // Ensure switch back in case of error
            return false;
        }
    }

}
