package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementHighlighter;
import java.time.Duration;
import java.util.Set;


public class EspaceClientPage {

    private final WebDriver driver;

    // Locators
    private final By espaceClientTitle =
            By.xpath("//div[contains(@class, 'panel') and contains(text(), 'Vous êtes')]");
    private final By professionnelSanteLink =
            By.xpath("//a[contains(@class, 'customer') and contains(text(), 'Professionnel de santé')]");

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

    // Click link
    public void clickProfessionnelSanteLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(professionnelSanteLink));
        ElementHighlighter.highlightClick(driver, el);

        // Store the current window handle
        String originalWindow = driver.getWindowHandle();

        // Click the link
        el.click();

        // Wait for the new window/tab to appear
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to the new tab
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Wait for the <h1> element with specific text to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Votre espace client assurance responsabilité civile et dommage aux biens')]")
        ));
    }

}
