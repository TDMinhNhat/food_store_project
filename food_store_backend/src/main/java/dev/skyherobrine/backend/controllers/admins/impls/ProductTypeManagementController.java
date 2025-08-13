package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.ProductTypeDto;
import dev.skyherobrine.backend.models.oracle.ProductType;
import dev.skyherobrine.backend.repositories.oracle.ProductTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/product_type")
@Tag(name = "Product Type Management (Admin - Warehouse)", description = "Manage product types in the warehouse")
@RequiredArgsConstructor
public class ProductTypeManagementController implements IManagement<ProductTypeDto, Long> {

    private final ProductTypeRepository productTypeRepository;

    @PostMapping
    @Operation(summary = "Add a new product type", description = "Create a new product type in the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add product type into database successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductType.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed for the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> add(@Valid @RequestBody ProductTypeDto dto) throws Exception {
        return ResponseEntity.ok(productTypeRepository.save(dto.toObject()));
    }

    @Override
    public ResponseEntity<Object> addMany(List<ProductTypeDto> list) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long aLong, ProductTypeDto productTypeDto) throws Exception {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product type", description = "Mark a product type as inactive in the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete product type successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductType.class))
            }),
            @ApiResponse(responseCode = "404", description = "The product type with the specified ID does not exist"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
        ProductType productType = productTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The product type with id " + id + " does not exist."));
        productType.setStatus(false);
        return ResponseEntity.ok(productTypeRepository.save(productType));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product type by ID", description = "Retrieve a product type from the warehouse by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get specific product type successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductType.class))
            }),
            @ApiResponse(responseCode = "404", description = "The product type with the specified ID does not exist"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(productTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The product type with id " + id + " does not exist.")));
    }

    @GetMapping
    @Operation(summary = "Get all product types", description = "Retrieve all product types from the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all product types successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductType.class))
            }),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAll() throws Exception {
        return ResponseEntity.ok(productTypeRepository.findAll());
    }

    @GetMapping("/page")
    @Operation(summary = "Get all product types by page", description = "Retrieve all product types from the warehouse with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all product types by page successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductType.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed for the page or size parameters"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAllByPage(
            @Valid @Min(value = 1, message = "The page number must be greater than or equal to 1")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "The size must be greater than or equal to 1")
            @RequestParam Integer size) throws Exception {
        return ResponseEntity.ok(productTypeRepository.getPageListProductType(Pageable.ofSize(size).withPage(page)));
    }
}
