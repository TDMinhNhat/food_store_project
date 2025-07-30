package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.Product}
 */
public record ProductDto(
        @Size(message = "Maximum length for 'productName' is 100 characters.", max = 100)
        @NotBlank(message = "The property 'productName' is required.")
        String productName,

        @Size(message = "Maximum length for 'manufacturer' is 50 characters", max = 50)
        @NotBlank(message = "The property 'manufacturer' is required.")
        String manufacturer,

        @Size(message = "Maximum length for 'originCountry' is 50 characters.", max = 50)
        @NotBlank(message = "The property 'originCountry' is required.")
        String originCountry,

        @Size(message = "Maximum length for 'unit' is 50 characters", max = 50)
        @NotBlank(message = "The property 'unit' is required.")
        String unit,

        @NotBlank(message = "The property 'productTypeName' is required.")
        String productTypeName
) implements Serializable {
}