package api.endpoints;


import io.restassured.authentication.AuthenticationScheme;

public class API {
    private String baseUri;
    private String basePath;
    private AuthenticationScheme authenticationScheme;
    private ItemAPI itemAPI;
    private ClientAPI clientAPI;
    private InvoiceAPI invoiceAPI;
    private LoginAPI loginAPI;
    private BankAccountAPI bankAccountAPI;

    public API(String baseUri, String basePath, AuthenticationScheme authenticationScheme) {
        this.baseUri = baseUri;
        this.basePath = basePath;
        this.authenticationScheme = authenticationScheme;
    }


    public ItemAPI itemAPI() {
        if (itemAPI == null) {
            itemAPI = new ItemAPI(baseUri, basePath, authenticationScheme);
        }
        return itemAPI;
    }

    public ClientAPI clientAPI() {
        if (clientAPI == null) {
            clientAPI = new ClientAPI(baseUri, basePath, authenticationScheme);
        }
        return clientAPI;
    }

    public LoginAPI loginAPI() {
        if (loginAPI == null) {
            loginAPI = new LoginAPI(baseUri, basePath, authenticationScheme);
        }
        return loginAPI;
    }

    public InvoiceAPI invoiceAPI() {
        if (invoiceAPI == null) {
            invoiceAPI = new InvoiceAPI(baseUri, basePath, authenticationScheme);
        }
        return invoiceAPI;
    }

    public BankAccountAPI bankAccountAPI() {
        if (bankAccountAPI == null) {
            bankAccountAPI = new BankAccountAPI(baseUri, basePath, authenticationScheme);
        }
        return bankAccountAPI;
    }

}
