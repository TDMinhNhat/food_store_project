package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderDetailDto(
        @NotBlank(message = "The property 'productId' is required.")
        String productId,
        @Positive(message = "Property 'quantity' must be a positive number and greater than 0")
        @NotNull(message = "The property 'quantity' is required.")
        Double quantity
) {
}
