package org.mrunmayi.restaurant.dto.ProductDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(

        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        double price
) {
}
