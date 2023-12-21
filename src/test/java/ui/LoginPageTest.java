package ui;

import config.Constants;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.core.WebApp;
import ui.pages.HomePage;
import ui.pages.LoginPage;

public class LoginPageTest {
    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver(); //Creates new chrome instance
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit(); //Kills browser
        }
    }

    @Test
    @DisplayName("Can login with valid credentials")
    @Tag("ui")
    @Description("Users with valid credentials can login")
    public void canLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        //Navigate to the Login page
        loginPage.open();
        //Check that navigation was successful
        Assertions.assertEquals("Вход в inv.bg", loginPage.getMainHeading());
        //Log in
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        //Check that user is logged in
        HomePage homePage = new HomePage(driver);
        String panelHeader = homePage.getUserPanelHeader();
        Assertions.assertEquals(Constants.EMAIL, panelHeader);
    }

    @Test
    @DisplayName("Can login and logout")
    @Tag("ui")
    @Description("Users with active session can logout successfully")
    public void canLogout() {
        WebApp webApp = new WebApp(driver);
        //Navigate to the Login page
        webApp.loginPage().open();
        //Check that navigation was successful
        Assertions.assertEquals("Вход в inv.bg", webApp.loginPage().getMainHeading());
        //Log in
        webApp.loginPage().login(Constants.EMAIL, Constants.PASSWORD);
        //Check that user is logged in
        String panelHeader = webApp.homePage().getUserPanelHeader();
        Assertions.assertEquals(Constants.EMAIL, panelHeader);
        //Logout
        webApp.homePage().logout();
        //Check that user is redirected to Login page Вход - QA Ground
        String title = webApp.loginPage().getCurrentPageTitle();
        Assertions.assertEquals("Вход - QA Ground", title);
        //Check success logout message
        Assertions.assertEquals("Вие излязохте от акаунта си.", webApp.loginPage().getLogoutMessage());
    }
}
