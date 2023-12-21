package api.v2;

import api.endpoints.ItemAPI;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseAPITestV2 {
    private static final String baseUri = "https://st2016.inv.bg";
    private static final String basePath = "/RESTapi";
    private static final String email = "karamfilovs@gmail.com";
    private static final String password = "111111";
    protected ItemAPI itemAPI;

    @BeforeAll
    public void beforeAll() {
        //Basic auth base64 encoded string from (username:password)
        //Header: Authorization | Basic encoded string
        PreemptiveBasicAuthScheme basicAuthScheme = new PreemptiveBasicAuthScheme();
        basicAuthScheme.setUserName(email);
        basicAuthScheme.setPassword(password);
        itemAPI = new ItemAPI(baseUri, basePath, basicAuthScheme);
    }
}
