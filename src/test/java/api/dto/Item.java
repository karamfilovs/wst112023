package api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Item {
    //Only mandatory fields: https://api.inv.bg/v3/swagger-ui/#/items/postItem
    private String name;
    private Double price;
    @SerializedName("quantity_unit")
    private String quantityUnit;
    @SerializedName("price_for_quantity")
    private Integer priceForQuantity;
    private String currency;
}
