package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementWaiter {

    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * Wait until the element located by the given locator is visible on the page.
     *
     * @param driver  The WebDriver instance
     * @param locator The By locator of the element
     * @return The visible WebElement
     */
    public static WebElement visible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait until the element located by the given locator is clickable.
     *
     * @param driver  The WebDriver instance
     * @param locator The By locator of the element
     * @return The clickable WebElement
     */
    public static WebElement clickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait until the element located by the given locator is present in the DOM (not necessarily visible).
     *
     * @param driver  The WebDriver instance
     * @param locator The By locator of the element
     * @return The WebElement present in the DOM
     */
    public static WebElement present(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
