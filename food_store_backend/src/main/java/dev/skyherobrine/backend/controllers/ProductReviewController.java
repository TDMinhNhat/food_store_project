package dev.skyherobrine.backend.controllers;

import dev.skyherobrine.backend.dtos.ProductReviewDto;
import dev.skyherobrine.backend.models.mongodb.ProductReview;
import dev.skyherobrine.backend.services.ProductReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product_review")
@Tag(name = "Reviews Product", description = "Doing some actions invoke about reviews")
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    @PostMapping
    @Operation(summary = "Add a review to a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add a review successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductReview.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the from data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> addReview(@Valid @RequestBody ProductReviewDto dto) {
        return ResponseEntity.ok(productReviewService.addReview(dto));
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "Update a review exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductReview.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form data"),
            @ApiResponse(responseCode = "404", description = "The review wasn't found in this product"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> updateReview(
            @PathVariable("reviewId") Long reviewId,
            @Valid @RequestBody ProductReviewDto dto) {
        return ResponseEntity.ok(productReviewService.updateReview(reviewId, dto));
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Delete a review exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete review successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductReview.class))
            }),
            @ApiResponse(responseCode = "404", description = "The review wasn't found in this product"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> dropReview(
            @PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(productReviewService.dropComment(reviewId));
    }
}
