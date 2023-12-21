package api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Client {
    private Integer id;
    private Boolean deleted;
    @SerializedName("is_person")
    private Boolean isPerson;
    private String name;
    private String town;
    private String bulstat;
    private String country;
}
