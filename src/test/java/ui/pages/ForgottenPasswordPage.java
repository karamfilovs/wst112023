package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgottenPasswordPage extends BasePage {
    private static final String URL = "/password-reset";
    private static final By emailFieldLocator = By.id("email");
    private static final By submitButtonLocator = By.id("submit");
    private static final By successMessage = By.xpath("//div[@class='alert selenium-message alert-success sticky']");
    private static final By passwordFieldLocator = By.name("password");
    private static final By password2FieldLocator = By.name("password2");
    private static final By submitNewPasswordLocator = By.id("submitbtn");

    public ForgottenPasswordPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters email
     * @param email email
     * @return ForgottenPasswordPage
     */
    public ForgottenPasswordPage enterEmail(String email){
        type(emailFieldLocator, email);
        return this;
    }

    /**
     * Clicks Submit button
     * @return ForgottenPasswordPage
     */
    public ForgottenPasswordPage clickSubmitButton(){
        click(submitButtonLocator, "Submit");
        return this;
    }

    /**
     * Retrieve success message
     * @return String
     */
    public String getSuccessMessage(){
        return getText(successMessage);
    }
}
