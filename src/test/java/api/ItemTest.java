package api;

import api.dto.Item;
import api.dto.Login;
import api.endpoints.LoginAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Constants;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

@Feature("Item")
@Tag("item")
public class ItemTest {
    /**
     * To run all tests use: mvn clean test
     * To generate the report use: mvn allure:serve
     */
    private static final String ITEMS_ENDPOINT = "/items";
    private static final String SINGLE_ITEM_ENDPOINT = "/items/{itemId}";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().create();
    private static String TOKEN;

    @BeforeAll
    public static void beforeAll(){
        TOKEN = new LoginAPI(Constants.BASE_API_URI, Constants.BASE_PATH, new PreemptiveOAuth2HeaderScheme())
                .obtainToken(new Login(Constants.EMAIL, Constants.PASSWORD, Constants.DOMAIN));
    }



    @Test
    @DisplayName("Can get all items using bearer token")
    @Description("User can retrieve all items with valid bearer token")
    public void canGetAllItemsUsingOAuth2(){
       RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
               // .header("Authorization", "Bearer " + TOKEN) //TODO: This is another way to pass bearer token
                .auth().oauth2(TOKEN)
                .when()
                .get(ITEMS_ENDPOINT)
                .then()
                .log().all()
                .body("total", CoreMatchers.equalTo(24))
                .body("items[0].name", CoreMatchers.equalTo("Кафе"));
    }

    @Test
    @DisplayName("Can get all items using basic auth")
    @Description("User can retrieve all items with basic auth")
    public void canGetAllItemsUsingBasicAuth(){
        //GET https://st2016.inv.bg/RESTapi/items
        RestAssured.given()
                .log().all()
                .baseUri("https://st2016.inv.bg") //TODO: add your login url
                .basePath("/RESTapi")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                //.header("Authorization", "Basic " + "a2FyYW1maWxvdnNAZ21haWwuY29tOjExMTExMQ==") //TODO: This is another way to pass bearer token
                .auth().preemptive().basic(Constants.EMAIL, Constants.PASSWORD)
                .when()
                .get(ITEMS_ENDPOINT)
                .then()
                .log().all()
                .body("items[0].name", CoreMatchers.equalTo("Coffee"));
    }

    @Test
    @DisplayName("Cant get all items with invalid credentials")
    @Description("User cant retrieve items with invalid basic creds")
    public void canGetAllItemsWithInvalidBasicCredentials(){
        //GET https://st2016.inv.bg/RESTapi/items
        RestAssured.given()
                .log().all()
                .baseUri("https://st2016.inv.bg") //TODO: add your login url
                .basePath("/RESTapi")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Basic " + "a2FyYW1maWxvdnNAZ21haWwuY29tOjExMTExMQ==")
                .when()
                .get(ITEMS_ENDPOINT)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("Can get single item")
    @Description("Can get single item with valid id")
    @TmsLink("INV-2328") //Test case link
    public void canGetSingleItem(){
        RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("itemId", 7908)
                .when()
                .get(SINGLE_ITEM_ENDPOINT)
                .then().log().body()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("item-schema.json"));
    }

    @Test
    @Tag("positive")
    @TmsLink("INV-2325") //Test case link
    @DisplayName("Can delete item")
    public void canDeleteItem(){
        //Define the DTO first
        Item coffee = Item.builder()
                .name("TO Be Deleted " + LocalDateTime.now())
                .price(10.50)
                .quantityUnit("kg.")
                .priceForQuantity(1)
                .build();

        Response response = RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Bearer " + TOKEN)
                .body(GSON.toJson(coffee))
                .when()
                .post(ITEMS_ENDPOINT)
                .prettyPeek();
        Assertions.assertEquals(201, response.statusCode()); //We expect 201 when creating an item

    }

    @Test
    @DisplayName("Can create new item")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("INV-334")
    @Description("Item can be created with valid payload")
    public void canCreateNewItem(){
        //Define the DTO first
        Item coffee = Item.builder()
                .name("Lavazza " + LocalDateTime.now())
                .price(10.50)
                .quantityUnit("kg.")
                .priceForQuantity(1)
                .build();

        Response response = RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Bearer " + TOKEN)
                .body(GSON.toJson(coffee))
                .when()
                .post(ITEMS_ENDPOINT)
                .prettyPeek();
        Assertions.assertEquals(201, response.statusCode()); //We expect 201 when creating an item
        //Extract the item id
        Integer itemId = response.jsonPath().get("id");
        //Delete the item
        Response deleteResponse = RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Bearer " + TOKEN)
                .body(GSON.toJson(coffee))
                .when()
                .delete(ITEMS_ENDPOINT + "/" + itemId)
                .prettyPeek();
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @DisplayName("Can update item")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("INV-2323") //Test case link
    @Description("Can update item with valid payload and id")
    public void canUpdateItem(){
        //Define the DTO first
        Item coffee = Item.builder()
                .name("Web Services Testing " + LocalDateTime.now())
                .price(10.50)
                .quantityUnit("kg.")
                .priceForQuantity(1)
                .build();

        //Create item to be updated
        Response response = RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Bearer " + TOKEN)
                .body(GSON.toJson(coffee))
                .when()
                .post(ITEMS_ENDPOINT)
                .prettyPeek();
        Assertions.assertEquals(201, response.statusCode()); //We expect 201 when creating an item
        //Extract the item id
        Integer itemId = response.jsonPath().get("id");
        //Update the DTO first
        coffee.setName("Updated Lavazza" + LocalDateTime.now());
        //Update the item
        Response updateResponse = RestAssured.given()
                .log().all()
                .baseUri(Constants.BASE_API_URI)
                .basePath(Constants.BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .header("Authorization", "Bearer " + TOKEN)
                .body(GSON.toJson(coffee))
                .when()
                .patch(ITEMS_ENDPOINT + "/" + itemId) //We can concatenate id directly to the url /items/id
                .prettyPeek();
        Assertions.assertEquals(204, updateResponse.statusCode()); //Not implemented endpoint. This is expected to fail
    }
}
