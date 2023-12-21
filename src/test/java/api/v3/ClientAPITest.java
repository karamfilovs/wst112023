package api.v3;

import api.dto.Client;
import api.dto.ClientRes;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ClientAPITest extends BaseAPITest {

    @Test
    @DisplayName("Can talk to different apis at the same time")
    public void canTestMultipleAPIs(){
        api.itemAPI().getItems();
        api.clientAPI().getClients();
        api.bankAccountAPI().getBankAccounts();
    }

    @Test
    @DisplayName("Can get all clients")
    @Description("Users can get all clients")
    @TmsLink("inv-1213")
    public void canGetAllClients(){
        Response getResponse = api.clientAPI().getClients();
        Assertions.assertEquals(200, getResponse.statusCode());
        ClientRes clientRes = GSON.fromJson(getResponse.asString(), ClientRes.class);
        Assertions.assertEquals(6, clientRes.getTotal());
        List<Client> clients = clientRes.getClients();
        Assertions.assertEquals(6, clients.size());
        clients.forEach(client -> Assertions.assertNotNull(client.getId()));
        List<Client> clients1  = getResponse.jsonPath().getList("clients");
        System.out.println(clients1);
    }
}
