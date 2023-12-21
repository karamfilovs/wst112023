package ui.pages;

import config.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    private WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger("BasePage.class");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void open(String pageUrl) {
        LOGGER.info("Navigating to page:" + pageUrl);
        driver.get(Constants.BASE_URI + pageUrl);
    }

    protected void type(By by, String text) {
        LOGGER.info("Typing text:" + text);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By by, String elementName) {
        LOGGER.info("Clicking element:" + elementName);
        WebElement element = driver.findElement(by);
        element.click();
    }

    protected String getText(By by) {
        String text = driver.findElement(by).getText().strip();
        LOGGER.info("Text found:" + text);
        return text;
    }

    public String getCurrentPageTitle() {
        String pageTitle = driver.getTitle();
        LOGGER.info("Page title: " + pageTitle);
        return pageTitle;
    }


}
