package api.v2;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemAPIV2Test extends BaseAPITestV2 {

    @Test
    @DisplayName("Can get all items using v2 of the api")
    @Description("User can get items using older version of the API")
    public void canGetAllItems(){
        Response response = itemAPI.getItems();
        Assertions.assertEquals(200, response.statusCode());
    }
}
