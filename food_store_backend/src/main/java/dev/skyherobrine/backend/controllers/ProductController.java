package dev.skyherobrine.backend.controllers;

import dev.skyherobrine.backend.projects.ProductProject;
import dev.skyherobrine.backend.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Get Products", description = "Access and get products information from system")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/page")
    @Operation(summary = "Get all products with page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all products with page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductProject.class))
            }),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getAllProductsPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(productService.getAllProductsPage(page, size));
    }

    @GetMapping("/sort")
    @Operation(summary = "Get all products with sorts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all products with sorts", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductProject.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed of the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getAllProductsSort(
            @Valid
            @Size(min = 1, message = "Require minimum 1 property need to sort")
            @RequestBody Map<String,String> sorting
    ) {
        return ResponseEntity.ok(productService.getAllProductsSort(sorting));
    }

    @GetMapping("/type/page")
    @Operation(summary = "Get all products by product type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all products with product type condition", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductProject.class))
            }),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getAllProductsByType(
            @RequestParam String type,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(productService.getAllProductsByType(type, page, size));
    }
}
