package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.ProductDto;
import dev.skyherobrine.backend.enums.ProductStatus;
import dev.skyherobrine.backend.models.oracle.Product;
import dev.skyherobrine.backend.repositories.oracle.ProductRepository;
import dev.skyherobrine.backend.services.admins.ProductManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/product")
@Tag(name = "Product Management (Admin - Warehouse)", description = "Manage products in the warehouse")
@RequiredArgsConstructor
public class ProductManagementController implements IManagement<ProductDto, Long> {

    private final ProductManagementService productManagementService;
    private final ProductRepository productRepository;

    @PostMapping
    @Operation(summary = "Add a new product to the warehouse",
            description = "This endpoint allows admins to add a new product to the warehouse. " +
                    "The product details should be provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed from the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> add(ProductDto dto) throws Exception {
        return ResponseEntity.ok(productManagementService.addProduct(dto));
    }

    @Override
    public ResponseEntity<Object> addMany(List<ProductDto> list) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long aLong, ProductDto productDto) throws Exception {
        return null;
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update an existing product in the warehouse",
            description = "This endpoint allows admins to update the details of an existing product in the warehouse by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
            }),
            @ApiResponse(responseCode = "404", description = "Some properties wasn't found in the database"),
            @ApiResponse(responseCode = "400", description = "Validation failed from the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> update(@PathVariable("productId") String productId, @Valid @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productManagementService.updateProduct(productId, dto));
    }

    @Override
    public ResponseEntity<Object> delete(Long aLong) throws Exception {
        return null;
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product from the warehouse",
            description = "This endpoint allows admins to delete a product from the warehouse by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
            }),
            @ApiResponse(responseCode = "404", description = "The product id wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> delete(@PathVariable("productId") String productId) throws Exception {
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new Exception("The product id with " + productId + " wasn't found in the database"));
        product.setStatus(ProductStatus.DISCONTINUED);
        return ResponseEntity.ok(productRepository.save(product));
    }

    @Override
    public ResponseEntity<Object> getById(Long aLong) throws Exception {
        return null;
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get a product by its ID",
            description = "This endpoint allows admins to retrieve a product from the warehouse by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
            }),
            @ApiResponse(responseCode = "404", description = "The product id wasn't found in the database"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    public ResponseEntity<Object> getById(@PathVariable("productId") String productId) throws Exception {
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new Exception("The product id with " + productId + " wasn't found in the database"));
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @Operation(summary = "Get all products in the warehouse",
            description = "This endpoint allows admins to retrieve all products currently in the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
            }),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAll() throws Exception {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/page")
    @Operation(summary = "Get all products in the warehouse with pagination",
            description = "This endpoint allows admins to retrieve all products currently in the warehouse with pagination support.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed from the page or size parameters"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAllByPage(
            @Valid @Min(value = 1, message = "The page number must be a number greater or equal 1")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "The number of sizes product must be greater or equal 1")
            @RequestParam Integer size) throws Exception {
        return ResponseEntity.ok(productRepository.getPageListProducts(Pageable.ofSize(size).withPage(page)));
    }
}
