package api.v3;

import api.dto.Item;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

public class ItemAPITest extends BaseAPITest {

    @Test
    @DisplayName("Can create item")
    @Tag("api")
    @Description("Authenticated users can create items")
    public void canCreateNewItem() {
        Item item = Item.builder()
                .name("Software Testing 2024")
                .price(800d)
                .quantityUnit("pc.")
                .priceForQuantity(1)
                .build();
        Response createResponse = api.itemAPI()
                .createItem(item);
        Assertions.assertEquals(201, createResponse.statusCode());
        Integer id = createResponse.jsonPath().get("id");
        //Clean the item
        Response deleteResponse = api.itemAPI()
                .deleteItem(id);
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @DisplayName("Can get item")
    @Tag("api")
    @Description("Authenticated users can retrieve single item")
    public void canGetItem() {
        Item item = Item.builder()
                .name("Single Item Test 2023")
                .price(800d)
                .quantityUnit("pc.")
                .priceForQuantity(1)
                .build();
        Response createResponse = api.itemAPI().createItem(item);
        Assertions.assertEquals(201, createResponse.statusCode());
        Integer id = createResponse.jsonPath().get("id");
        //Get the item
        Response getResponse = api.itemAPI().getItem(id);
        Assertions.assertEquals(200, getResponse.statusCode());
        Item newItem = GSON.fromJson(getResponse.asString(), Item.class);
        Assertions.assertEquals(item.getName(), newItem.getName());
        Assertions.assertEquals(item.getPrice(), newItem.getPrice());
        Assertions.assertEquals("BGN", newItem.getCurrency(), "The default currency is not BGN");
        //Clean item
        api.itemAPI().deleteItem(id);
    }

    @Test
    @Disabled //This is not implemented
    @DisplayName("Can update item")
    @Tag("api")
    @Issue("IN-70")
    @Description("Authenticated users can update existing items")
    public void canUpdateItem() {
        //Create item dto
        Item item = Item.builder()
                .name("TOBE_UPDATED")
                .price(800d)
                .quantityUnit("pc.")
                .priceForQuantity(1)
                .build();
        //Create item
        Response createResponse = api.itemAPI().createItem(item);
        Assertions.assertEquals(201, createResponse.statusCode());
        Integer id = createResponse.jsonPath().get("id");
        //Update item
        item.setName("UPDATE_ITEM");
        item.setCurrency("EUR");
        Response updateResponse = api.itemAPI().updateItem(id, item);
        Assertions.assertEquals(501, updateResponse.statusCode());
        //Clean the item
        Response deleteResponse = api.itemAPI().deleteItem(id);
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @DisplayName("Can delete item")
    @Tag("api")
    @Description("Authenticated users can delete single item")
    public void canDeleteItem() {
        Item item = Item.builder()
                .name("TOBE_DELETED")
                .price(800d)
                .quantityUnit("pc.")
                .priceForQuantity(1)
                .build();
        Response createResponse = api.itemAPI().createItem(item);
        Assertions.assertEquals(201, createResponse.statusCode());
        Integer id = createResponse.jsonPath().get("id");
        //Delete the item
        Response deleteResponse = api.itemAPI().deleteItem(id);
        Assertions.assertEquals(204, deleteResponse.statusCode());
        //Get the item
        Response getResponse = api.itemAPI().getItem(id);
        Assertions.assertEquals(404, getResponse.statusCode());
        String error = getResponse.jsonPath().get("error");
        Assertions.assertEquals("Item Not Found", error);
    }
}
