package dev.skyherobrine.backend.dtos;

import dev.skyherobrine.backend.models.mongodb.ProductDescription;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DTO for {@link dev.skyherobrine.backend.models.mongodb.ProductDescription}
 */
public record ProductDescriptionDto(
        @NotBlank(message = "The property 'productId' is required.")
        String productId,

        @Size(min = 1, message = "The property 'descriptions' must contain at least one description.")
        Map<String, String> descriptions
) implements Serializable {

    public ProductDescription toObject() {
        return new ProductDescription(productId, descriptions);
    }
}