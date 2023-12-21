package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By userPanelLocator = By.xpath("//div[@class='userpanel-header']");
    private static final By logoutLink = By.cssSelector("a.selenium-button-logout");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getUserPanelHeader() {
        return getText(userPanelLocator);
    }

    /**
     * Log out from the system
     */
    public void logout() {
        click(userPanelLocator, "user panel header"); //First we need to click panel header to expand the dropdown
        click(logoutLink, "logout link");
    }
}
