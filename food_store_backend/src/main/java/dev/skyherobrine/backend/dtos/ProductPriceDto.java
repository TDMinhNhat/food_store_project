package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.ProductPrice}
 */
public record ProductPriceDto(
        @NotNull(message = "The property 'priceDate' is required.")
        LocalDateTime priceDate,

        @NotBlank(message = "The property 'productId' is required.")
        String productId,

        @NotNull(message = "The property 'price' is required.")
        @Positive(message = "The property 'price' must be a positive number and not equal zero.")
        Double price
) implements Serializable {
}