package org.mrunmayi.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(

        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        double price
) {
}
