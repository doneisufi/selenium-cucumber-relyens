package pages;

import org.openqa.selenium.By;
import utils.ElementHighlighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RelyensPage {

    private final WebDriver driver;

    // Locators
    private final By relationClientTitle =
            By.xpath("//section[@id='page']/div/h1[contains(text(), 'La satisfaction clients')]");

    // Constructor
    public RelyensPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public boolean isRelyensPageDisplayed() {
        WebElement el = driver.findElement(relationClientTitle);
        ElementHighlighter.highlightVerify(driver, el);
        return el.isDisplayed();
    }

}
