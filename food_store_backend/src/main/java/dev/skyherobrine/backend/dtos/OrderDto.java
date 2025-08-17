package dev.skyherobrine.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.Order}
 */
public record OrderDto(
        @NotBlank(message = "The property 'customerId' is required.")
        String customerId,
        @NotBlank(message = "The property 'employeeId' is required.")
        String employeeId,
        @NotNull(message = "The property 'orderDetails' is required")
        @Size(min = 1, message = "Minimum order 1 product")
        List<OrderDetailDto> orderDetails
) implements Serializable {

}