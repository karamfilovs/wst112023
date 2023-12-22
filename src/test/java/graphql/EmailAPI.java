package graphql;

import api.core.Request;
import config.Constants;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;
import utils.RetryOn;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EmailAPI extends Request {
    private static final String ENDPOINT = "/graphql";

    public EmailAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    /**
     * Retrieve all emails from test mail graphql service
     * @return Email
     */
    public Inbox getInbox(GraphQuery query){
        Response response = post(ENDPOINT, GSON.toJson(query));
        GraphData emailData = GSON.fromJson(response.asString(), GraphData.class);
        return emailData.getData()
                .getInbox();
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

    /**
     * It will poll the email api for max 20s until the count changes (new email arrives)
     * @param currentEmailCount number of emails before the email was sent
     */
    public void waitForEmailToArrive(int currentEmailCount) {
        GraphQuery query = new GraphQuery();
        query.setQuery("{inbox (namespace:\"wav4e\") {result message count emails { subject text }}}");
        Supplier<Inbox> actionToRetry = () -> getInbox(query);
        Predicate<Inbox> retryIf = inbox -> (inbox.getCount() == currentEmailCount);
        new RetryOn<Inbox>().result(actionToRetry, retryIf, 10, 2);
    }


}
