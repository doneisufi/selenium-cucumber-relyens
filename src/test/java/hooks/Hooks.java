package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Check if running in headless mode (e.g., in CI)
        if ("true".equalsIgnoreCase(System.getenv("HEADLESS"))) {
            options.addArguments("--headless=new"); // modern headless mode
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            System.out.println("Running in headless mode.");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        System.out.println("WebDriver initialized in @Before hook.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver quit in @After hook.");
        }
    }
}
