package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementHighlighter {

    /**
     * Generic highlight method with customizable colors and duration
     */
    public static void highlight(WebDriver driver, WebElement element, String borderColor, String backgroundColor, int durationMs) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");

        try {
            // Apply highlight style
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 2px solid " + borderColor + "; background: " + backgroundColor + ";");
            Thread.sleep(durationMs); // keep highlight visible
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Restore original style
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, originalStyle);
        }
    }

    /**
     * Shortcut for click actions (blue border, yellow background)
     */
    public static void highlightClick(WebDriver driver, WebElement element) {
        highlight(driver, element, "blue", "yellow", 500);
    }

    /**
     * Shortcut for verification actions (green border, lightgreen background)
     */
    public static void highlightVerify(WebDriver driver, WebElement element) {
        highlight(driver, element, "green", "lightgreen", 800);
    }

    /**
     * Shortcut for input actions (blue border, lightgreen background)
     */
    public static void highlightInput(WebDriver driver, WebElement element) {
        highlight(driver, element, "blue", "lightblue", 500);
    }

}
