package api.endpoints;

import api.core.Request;
import api.dto.Login;
import config.Constants;
import io.qameta.allure.Step;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.response.Response;

public class LoginAPI extends Request {
    private static final String ENDPOINT = "/login/token";

    public LoginAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    /**
     * Obtains new token for the inv.bg api
     * @return token as string
     */
    @Step("Obtain token")
    public String obtainToken(Login login){
        Response response = post(ENDPOINT, GSON.toJson(login));
        //return response.then().extract().jsonPath().get("token"); //This extracts the token. Doing the same thing like the next line but more verbose
        return response.jsonPath().get("token");
    }

    public static void main(String[] args) {
        AuthenticationScheme scheme = new OAuth2Scheme();
        LoginAPI loginAPI = new LoginAPI(Constants.BASE_API_URI, Constants.BASE_PATH, scheme);
        Login alexCredentials = new Login(Constants.EMAIL, Constants.PASSWORD, Constants.DOMAIN);
        String token = loginAPI.obtainToken(alexCredentials);
        System.out.println("The token is:" + token);
    }
}
