package ui;

import api.v3.BaseAPITest;
import config.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        if(Constants.HEADLESS){
            chromeOptions.addArguments("--headless=new");
        }
        driver = new ChromeDriver(chromeOptions); //Creates new chrome instance
        webApp = new WebApp(driver);
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit(); //Kills browser
        }
    }
}
