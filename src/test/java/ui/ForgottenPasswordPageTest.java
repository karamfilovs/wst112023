package ui;

import config.Constants;
import graphql.Email;
import graphql.GraphQuery;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.TextUtils;

import java.util.List;

@Tag("forgotten-password")
public class ForgottenPasswordPageTest extends BaseUITest {

    @Test
    @Tag("ui")
    @DisplayName("Can reset password")
    @Description("Existing  users can restore password via email")
    public void canRestorePassword() throws InterruptedException {
        String successMessage = "На e-mail адреса Ви беше изпратен линк, чрез който можете да смените паролата си.";
        webApp.loginPage().open();
        Assertions.assertEquals("Вход в inv.bg", webApp.loginPage().getMainHeading());
        //Click forgotten link
        webApp.loginPage().clickResetPassword();
        //Populate existing email and submit
        webApp.forgottenPasswordPage().enterEmail(Constants.RESTORE_EMAIL).clickSubmitButton();
        //Check success message
        Assertions.assertEquals(successMessage, webApp.forgottenPasswordPage().getSuccessMessage());
        //Wait for the email to arrive
        Thread.sleep(6000);
        //Check email (api/ui)
        GraphQuery query = new GraphQuery();
        query.setQuery("{inbox (namespace:\"wav4e\") {result message count emails { subject text }}}");
        List<Email> emails = emailAPI.getEmails(query);
        //Check that the inbox email count increases with 1
        Assertions.assertTrue(emails.size() > 1);
        //Extract reset password link from the email body
        Email targetEmail = emails.get(0);
        //Pattern to extract the url
        List<String> urls = TextUtils.extractUrls(targetEmail.getText());
        urls.forEach(System.out::println);
        //TODO: Finish the test at home

    }
}
