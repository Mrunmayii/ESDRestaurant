package org.mrunmayi.restaurant.dto.CustomerDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("firstName")
        String firstName,
        @JsonProperty("lastName")
        String lastName,
        @JsonProperty("email")
        String email,
        @JsonProperty("addr")
        String addr,
        @JsonProperty("city")
        String city,
        @JsonProperty("pinCode")
        String pinCode
) {
}
