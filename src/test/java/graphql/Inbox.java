package graphql;

import lombok.Getter;

import java.util.List;

@Getter
public class Inbox {
    private String result;
    private String message;
    private Integer count;
    private List<Email> emails;
}
