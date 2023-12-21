package api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class ClientRes {
    private Integer total;
    @SerializedName("prev_page")
    private String previousPage;
    @SerializedName("next_page")
    private String nextPage;
    List<Client> clients;
}
