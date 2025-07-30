package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link dev.skyherobrine.backend.models.mongodb.ImportProduct}
 */
public record ImportProductDto(
        @NotBlank(message = "The property 'productId' is required.")
        String productId,

        @NotBlank(message = "The property 'supplyName' is required.")
        String supplyName,

        @NotNull(message = "The property 'quantity' is required.")
        @Positive(message = "The property 'quantity' must be a positive number and not equal zero.")
        Double quantity
) implements Serializable {
}