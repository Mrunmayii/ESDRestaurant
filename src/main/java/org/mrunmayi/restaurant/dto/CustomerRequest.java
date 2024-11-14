package org.mrunmayi.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @NotNull(message="Customer email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present")
        @NotEmpty(message = "Password should be present")
        @NotBlank(message = "Password should be present")
        @Size(min = 6, max = 12)
        @JsonProperty("password")
        String password,

        @NotNull(message="Address is required")
        @NotEmpty(message = "Address should be present")
        @NotBlank(message = "Address should be present")
        @JsonProperty("addr")
        String addr,

        @NotNull(message="City is required")
        @NotEmpty(message = "City required")
        @NotBlank(message = "City required")
        @JsonProperty("city")
        String city,

        @NotNull(message="Pincode is required")
        @NotEmpty(message = "Pincode required")
        @NotBlank(message = "Pincode required")
        @JsonProperty("pinCode")
        String pinCode
) {
}
