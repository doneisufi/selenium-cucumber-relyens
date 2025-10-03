package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementHighlighter {

    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        // Add highlight
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, "border: 2px solid red; background: yellow;");
        try {
            Thread.sleep(300); // highlight visible for 300ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Restore original style
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, originalStyle);
    }
}
