package ui.core;

import org.openqa.selenium.WebDriver;
import ui.pages.*;

public class WebApp {
    private WebDriver driver;
    //Pages
    private LoginPage loginPage;
    private HomePage homePage;
    private ItemPage itemPage;
    private ClientPage clientPage;
    private InvoicePage invoicePage;
    private ForgottenPasswordPage forgottenPasswordPage;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public ItemPage itemPage() {
        if (itemPage == null) {
            itemPage = new ItemPage(driver);
        }
        return itemPage;
    }

    public ForgottenPasswordPage forgottenPasswordPage() {
        if (forgottenPasswordPage == null) {
            forgottenPasswordPage = new ForgottenPasswordPage(driver);
        }
        return forgottenPasswordPage;
    }
}
