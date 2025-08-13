package dev.skyherobrine.backend.dtos;

import dev.skyherobrine.backend.models.mongodb.ProductReview;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link dev.skyherobrine.backend.models.mongodb.ProductReview}
 */
public record ProductReviewDto(
        @NotBlank(message = "The property 'productId' is required.")
        String productId,

        @NotBlank(message = "The property 'userId' is required.")
        String userId,

        @NotNull(message = "The property 'rating' is required.")
        @Range(message = "The range of 'rating' between 0 and 5.", min = 0, max = 5)
        Double rating,

        @NotBlank(message = "The property 'comment' is required.")
        String comment
) implements Serializable {

}