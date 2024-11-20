package org.mrunmayi.restaurant.dto.ProductDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class ProductRequest{

        public record ProductCreateRequest(
                @NotNull(message = "Customer should be present")
                @NotEmpty(message = "Customer should be present")
                @NotBlank(message = "Customer should be present")
                @JsonProperty("name")
                String name,

                @JsonProperty("price")
                Double price
        ) {

        }

        public record ProductUpdateRequest(
                @JsonProperty("name")
                String name,

                @JsonProperty("price")
                Double price
        ) {

        }
}