package api.v3;

import api.dto.Login;
import api.endpoints.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Constants;
import graphql.EmailAPI;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseAPITest {

    protected API api;
    protected EmailAPI emailAPI;
    protected final Gson GSON = new GsonBuilder()  //Gson instance to be able to serialize/deserialize java objects
            .setPrettyPrinting()
            .create();

    @BeforeAll
    public void beforeAll() {
        //Login in the API
        Login credentials = new Login(Constants.EMAIL, Constants.PASSWORD, Constants.DOMAIN);
        PreemptiveOAuth2HeaderScheme scheme = new PreemptiveOAuth2HeaderScheme();
        api = new API(Constants.BASE_API_URI, Constants.BASE_PATH, scheme);
        String token = api.loginAPI().obtainToken(credentials);
        //Create new item
        scheme.setAccessToken(token);
        //Email API
        PreemptiveOAuth2HeaderScheme graphScheme = new PreemptiveOAuth2HeaderScheme();
        graphScheme.setAccessToken(Constants.TEST_MAIL_TOKEN);
        emailAPI = new EmailAPI(Constants.TEST_MAIL_BASE_URI, "/api", graphScheme);
    }
}
