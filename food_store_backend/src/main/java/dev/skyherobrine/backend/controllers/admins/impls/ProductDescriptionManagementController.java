package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.ProductDescriptionDto;
import dev.skyherobrine.backend.repositories.mongodb.ProductDescriptionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/product_description")
@Tag(name = "Product Description Management (Admin - Warehouse)",
        description = "Manage product descriptions in the system")
@RequiredArgsConstructor
public class ProductDescriptionManagementController implements IManagement<ProductDescriptionDto, String> {

    private final ProductDescriptionRepository productDescriptionRepository;

    @PostMapping
    @Operation(summary = "Add a new product description",
            description = "This endpoint allows you to add a new product description to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully added the product description",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDescriptionDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed from the form data"),
            @ApiResponse(responseCode = "500", description = "The server returned an error when executing")
    })
    @Override
    public ResponseEntity<Object> add(@Valid @RequestBody ProductDescriptionDto dto) throws Exception {
        return ResponseEntity.ok(productDescriptionRepository.save(dto.toObject()));
    }

    @Override
    public ResponseEntity<Object> addMany(List<ProductDescriptionDto> list) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(String s, ProductDescriptionDto productDescriptionDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(String s) throws Exception {
        return null;
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product description by ID", description = "This endpoint retrieves a product description by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the product description",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDescriptionDto.class))),
            @ApiResponse(responseCode = "404", description = "Product description not found"),
            @ApiResponse(responseCode = "500", description = "The server returned an error when executing")
    })
    @Override
    public ResponseEntity<Object> getById(@PathVariable("productId") String productId) throws Exception {
        return ResponseEntity.ok(productDescriptionRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product description not found with ID: " + productId)));
    }

    @Override
    public ResponseEntity<Object> getAll() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> getAllByPage(Integer page, Integer size) throws Exception {
        return null;
    }
}
