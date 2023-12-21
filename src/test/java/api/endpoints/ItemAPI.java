package api.endpoints;

import api.core.Request;
import api.dto.Item;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

public class ItemAPI extends Request {
    private static final String ENDPOINT = "/items";
    private static final String SINGLE_ENDPOINT = "/items/{id}";

    public ItemAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    /**
     * Get single item
     *
     * @param id item id
     * @return Response
     */
    public Response getItem(int id) {
        return get(SINGLE_ENDPOINT, id);
    }

    /**
     * Delete single item
     *
     * @param id item id
     * @return Response
     */
    public Response deleteItem(int id) {
        return delete(SINGLE_ENDPOINT, id);
    }

    /**
     * Get all items
     *
     * @return Response
     */
    public Response getItems() {
        return get(ENDPOINT);
    }

    /**
     * Creates item
     *
     * @param item item information
     * @return Response
     */
    public Response createItem(Item item) {
        return post(ENDPOINT, GSON.toJson(item));
    }

    /**
     * Update item
     *
     * @param id   existing item id
     * @param item item information
     * @return Response
     */
    public Response updateItem(int id, Item item) {
        return patch(SINGLE_ENDPOINT, id, GSON.toJson(item));
    }
}
