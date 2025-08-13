package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.ProductImage}
 */
public record ProductImageDto(
        @NotBlank(message = "The property 'productId' is required.")
        String productId,

        @NotBlank
        String alternative) implements Serializable {
}