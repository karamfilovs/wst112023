package ui;

import api.v3.BaseAPITest;
import config.Constants;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.core.WebApp;

public class BaseUITest extends BaseAPITest {
    private WebDriver driver;
    protected WebApp webApp;

    @BeforeEach
    public void beforeEach() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Constants.HEADLESS) {
            chromeOptions.addArguments("--headless=new");
        }
        driver = new ChromeDriver(chromeOptions); //Creates new chrome instance
        webApp = new WebApp(driver);
    }

    @AfterEach
    public void afterEach() {
        saveScreenshotPNG(driver);
        if (driver != null) {
            driver.quit(); //Kills browser
        }
    }


    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
