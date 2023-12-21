package api.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Login {
    //https://api.inv.bg/v3/swagger-ui/#/account/getLoginToken
    //To be able to log in user needs to provide the following
    private String email;
    private String password;
    private String domain;
}
