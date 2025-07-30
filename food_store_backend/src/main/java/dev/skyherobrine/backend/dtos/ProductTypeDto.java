package dev.skyherobrine.backend.dtos;

import dev.skyherobrine.backend.models.oracle.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.ProductType}
 */
public record ProductTypeDto(
        @Size(message = "Maximum length for 'typeName' is 100 characters", max = 100)
        @NotBlank(message = "The property 'typeName' is required.")
        String typeName,

        @Size(max = 500)
        String description
) implements Serializable {

    public ProductType toObject() {
        return new ProductType(typeName, description);
    }
}