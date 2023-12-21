package api.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class Request {
    private String baseUri;
    private String basePath;
    private AuthenticationScheme scheme;
    protected final Gson GSON = new GsonBuilder()  //Gson instance to be able to serialize/deserialize java objects
            .setPrettyPrinting()
            .create();

    public Request (String baseUri, String basePath, AuthenticationScheme scheme){
        this.baseUri = baseUri;
        this.basePath = basePath;
        this.scheme = scheme;
    }

    /**
     * Sends HTTP Post request
     * @param url endpoint url for the request
     * @param body body for the request
     * @return Response
     */
    @Step("Sending POST request: to {url} with body: {body}")
    protected Response post(String url, String body) {
        return baseRequest()
                .body(body)
                .post(url);
    }

    /**
     * Sends HTTP PUT request
     * @param url endpoint url for the request
     * @param body body of the request
     * @return Response
     */
    protected Response put(String url, String body) {
        return baseRequest()
                .body(body)
                .put(url);
    }

    protected Response put(String url, int id,  String body) {
        return baseRequest()
                .pathParam("id", String.valueOf(id))
                .body(body)
                .put(url);
    }

    /**
     * Sends HTTP DELETE request
     * @param url endpoint url
     * @return Response
     */
    @Step("Sending DELETE request: to {url }")
    protected Response delete(String url) {
        return baseRequest().delete(url);
    }

    @Step("Sending DELETE request: to {url} with id: {id}")
    protected Response delete(String url, int id) {
        return baseRequest()
                .pathParam("id", String.valueOf(id))
                .delete(url);
    }

    /**
     * Sends GET request
     * @param url endpoint url for the request
     * @return Response
     */
    protected Response get(String url) {
        return baseRequest().get(url);
    }

    @Step("Sending GET request: to {url} with id: {id}")
    protected Response get(String url, int id) {
        return baseRequest()
                .pathParam("id", String.valueOf(id))
                .get(url);
    }

    /**
     * Sends PATCH request
     * @param url url for the request
     * @param body body for the request
     * @return Response
     */
    @Step("Sending PATCH request: to {url} with body: {body}")
    protected Response patch(String url, String body) {
        return baseRequest()
                .body(body)
                .patch(url);
    }

    @Step("Sending PATCH request: to {url} with id: {id} and body: {body}")
    protected Response patch(String url, int id, String body) {
        return baseRequest()
                .pathParam("id", String.valueOf(id))
                .body(body)
                .patch(url);
    }

    /**
     * This is the base request builder
     * @return RequestSpecification
     */
    private RequestSpecification baseRequest(){
        RestAssured.reset(); //Restore default state
        RestAssured.authentication = scheme; //This line sets the auth scheme for the api
        return RestAssured.given()
                .filter(new RequestLoggingFilter()) //This will force rest assured to print request details
                .filter(new ResponseLoggingFilter()) //This will force rest assured to print the response
                .baseUri(baseUri) //This will add base uri
                .basePath(basePath) //This will add base path
                .contentType(ContentType.JSON) // This will add content type header
                .accept(ContentType.JSON) //This will add accept header
                .header("User-Agent", "Mozilla"); //This is just to bypass the check from inv.bg
    }
}
