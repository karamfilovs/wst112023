package graphql;

import api.core.Request;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

import java.util.List;

public class EmailAPI extends Request {
    private static final String ENDPOINT = "/graphql";

    public EmailAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    /**
     * Retrieve all emails from test mail graphql service
     * @return Email
     */
    public List<Email> getEmails(GraphQuery query){
        Response response = post(ENDPOINT, GSON.toJson(query));
        GraphData emailData = GSON.fromJson(response.asString(), GraphData.class);
        return emailData.getData()
                .getInbox()
                .getEmails();
    }

    /**
     * Retrieve all emails from test mail graphql service
     * @return Email
     */
    public Response getInvalidEmails(GraphQuery query){
        Response response = post(ENDPOINT, GSON.toJson(query));
        return response;
    }

    /**
     * Retrieve all emails from test mail graphql service
     * @return Email
     */
    public List<Email> getEmails(String namespace){
        String queryString = String.format("{inbox (namespace: %s) {result message count emails { subject text }}}", namespace);
        GraphQuery query = new GraphQuery();
        query.setQuery(queryString);
        Response response = post(ENDPOINT, GSON.toJson(query));
        GraphData emailData = GSON.fromJson(response.asString(), GraphData.class);
        return emailData.getData()
                .getInbox()
                .getEmails();
    }
}
