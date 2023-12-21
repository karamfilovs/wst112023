package ui;

import config.Constants;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

//    @Test
//    @DisplayName("Can login with valid credentials")
//    @Tag("ui")
//    @Description("Users with valid credentials can login")
//    public void canLoginWithValidCredentials() {
//        driver.get(Constants.BASE_URI);
//        WebElement heading = driver.findElement(By.xpath("//h1"));
//        Assertions.assertEquals("Вход в inv.bg", heading.getText());
//        WebElement usernameField = driver.findElement(By.id("loginusername"));
//        usernameField.sendKeys(Constants.EMAIL);
//        WebElement passwordField = driver.findElement(By.id("loginpassword"));
//        passwordField.sendKeys(Constants.PASSWORD);
//        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
//        loginButton.click();
//        WebElement loggedUser = driver.findElement(By.xpath("//div[@class='userpanel-header']"));
//        Assertions.assertEquals(Constants.EMAIL, loggedUser.getText());
//    }
}
