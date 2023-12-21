package api.endpoints;

import api.core.Request;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

public class BankAccountAPI extends Request {
    private static final String ENDPOINT = "/bank/accounts";
    //TODO: Try to implement this at home
    public BankAccountAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    /**
     * Retrieves all bank accounts
     * @return Response
     */
    public Response getBankAccounts(){
        return get(ENDPOINT);
    }
}
