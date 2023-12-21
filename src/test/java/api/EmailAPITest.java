package api;

import api.v3.BaseAPITest;
import graphql.Email;
import graphql.GraphQuery;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmailAPITest extends BaseAPITest {

    @Test
    @DisplayName("Can get all emails from test mail app")
    @Description("Can retrieve emails from graphql api")
    public void canGetEmails(){
        GraphQuery query = new GraphQuery();
        query.setQuery("{inbox (namespace:\"wav4e\") {result message count emails { subject text }}}");
        List<Email> emails = emailAPI.getEmails(query);
        Assertions.assertEquals(2, emails.size());
        System.out.println("Target email body:\n" + emails.get(0).getText());
    }

    @Test
    @DisplayName("Cant get emails without specifying list of fields")
    @Description("Cant get emails without specifying list of fields")
    public void canGetEmailsWithoutSpecifyingListOfFields(){
        GraphQuery query = new GraphQuery();
        query.setQuery("{inbox (namespace:\"wav4e\") {result message count emails {}}}");
        Response response = emailAPI.getInvalidEmails(query);
        Assertions.assertEquals(400, response.statusCode());
        //TODO: Map it to Error object and assert that name is expected
    }
}
