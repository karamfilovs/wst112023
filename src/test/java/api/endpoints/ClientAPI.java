package api.endpoints;

import api.core.Request;
import api.dto.Client;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

public class ClientAPI extends Request {
    private static final String ENDPOINT = "/clients";
    private static final String SINGLE_ENDPOINT = "/clients/{id}";

    public ClientAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    /**
     * Create new client
     * @param client client information
     * @return Response
     */
    public Response createClient(Client client){
        return post(ENDPOINT, GSON.toJson(client));
    }

    /**
     * Retrieves client by id
     * @param id id of the client
     * @return Response
     */
    public Response getClient(int id){
        return get(SINGLE_ENDPOINT, id);
    }

    /**
     * Retrieves all clients
     * @return Response
     */
    public Response getClients(){
        return get(ENDPOINT);
    }

    /**
     * Deletes single client
     * @param id id of the client
     * @return Response
     */
    public Response deleteClient(int id){
        return delete(SINGLE_ENDPOINT, id);
    }

    /**
     * Updates single client
     * @param id id of the client
     * @param client client information
     * @return Response
     */
    public Response updateClient(int id, Client client){
        return patch(SINGLE_ENDPOINT, id, GSON.toJson(client));
    }
}
