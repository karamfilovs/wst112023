package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final String URL = "/login"; //The full address: https://st2016.inv.bg/login
    private static final By emailFieldLocator = By.id("loginusername");
    private static final By passwordFieldLocator = By.id("loginpassword");
    private static final By loginButtonLocator = By.id("loginsubmit");
    private static final By logoutMessageLocator = By.id("okmsg");
    private static final By headingLocator = By.xpath("//h1");
    private static final By forgottenPasswordLink = By.xpath("//a[contains(text(), 'Забравена')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the Login page
     */
    public void open() {
        open(URL);
    }

    /**
     * Performs login with username and password
     *
     * @param email    valid email
     * @param password valid password
     */
    public void login(String email, String password) {
        enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();
    }

    private LoginPage enterEmail(String email) {
        type(emailFieldLocator, email);
        return this;
    }

    private LoginPage enterPassword(String password) {
        type(passwordFieldLocator, password);
        return this;
    }

    private LoginPage clickLoginButton() {
        click(loginButtonLocator, "Login button");
        return this;
    }

    /**
     * Retrieves the page main heading
     *
     * @return String
     */
    public String getMainHeading() {
        return getText(headingLocator);
    }

    public String getLogoutMessage() {
        return getText(logoutMessageLocator);
    }

    /**
     * Clicks forgotten password link
     */
    public LoginPage clickResetPassword() {
        click(forgottenPasswordLink, "Forgotten password link");
        return this;
    }
}
