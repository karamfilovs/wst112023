package api;

import api.dto.Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenTest {
    private static final String LOGIN_ENDPOINT = "/login/token";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().create();
    private static final String BASE_URI = "https://api.inv.bg";
    private static final String BASE_PATH = "/v3";
    //TODO: Replace with your credentials
    private static final String USERNAME = "alex@qaground.com";
    private static final String PASSWORD = "111111";
    private static final String DOMAIN = "st2016";


    @Test
    @DisplayName("Can get bearer token")
    @Description("User can obtain token with valid credentials")
    public void canGetBearerToken(){
        Login myCredentials = new Login(USERNAME, PASSWORD, DOMAIN);
        RestAssured.given()
                .log().all()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .body(GSON.toJson(myCredentials))
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .log().body()
                .body("token", CoreMatchers.notNullValue())
                .body("expires_string", CoreMatchers.containsString("2023-12-13"));
    }
}
