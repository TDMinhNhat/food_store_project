package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.ImportProductDto;
import dev.skyherobrine.backend.models.mongodb.ImportProduct;
import dev.skyherobrine.backend.repositories.mongodb.ImportProductRepository;
import dev.skyherobrine.backend.services.admins.ImportProductManagementService;
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
@RequestMapping("/api/v1/admins/import_product")
@Tag(name = "Import Product Management (Admin - Warehouse)", description = "Manage imported products in the warehouse")
@RequiredArgsConstructor
public class ImportProductManagementController implements IManagement<ImportProductDto, Long> {

    private final ImportProductRepository importProductRepository;
    private final ImportProductManagementService importProductManagementService;

    @PostMapping
    @Operation(summary = "Add a new imported product",
            description = "This endpoint allows you to add a new imported product to the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the imported product", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ImportProduct.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed from the form data"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> add(@Valid @RequestBody ImportProductDto dto) throws Exception {
        return ResponseEntity.ok(importProductManagementService.addImportProduct(dto));
    }

    @Override
    public ResponseEntity<Object> addMany(List<ImportProductDto> list) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long aLong, ImportProductDto importProductDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long aLong) throws Exception {
        return null;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an imported product by ID",
            description = "This endpoint retrieves an imported product from the warehouse by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the imported product", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ImportProduct.class))
            }),
            @ApiResponse(responseCode = "404", description = "Imported product not found"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(importProductRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Imported product not found with ID: " + id)));
    }

    @GetMapping
    @Operation(summary = "Get all imported products",
            description = "This endpoint retrieves all imported products from the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all imported products", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ImportProduct.class))
            }),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAll() throws Exception {
        return ResponseEntity.ok(importProductRepository.findAll());
    }

    @GetMapping("/page")
    @Operation(summary = "Get all imported products by page",
            description = "This endpoint retrieves all imported products from the warehouse with pagination support.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all imported products by page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ImportProduct.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation failed from the page or size parameters"),
            @ApiResponse(responseCode = "500", description = "The server return an error when executing")
    })
    @Override
    public ResponseEntity<Object> getAllByPage(
            @Valid @Min(value = 1, message = "The page paramerter should be a positive number and greater than 0")
            @RequestParam(required = false, defaultValue = "1")
            Integer page,
            @Valid @Min(value = 1, message = "The size parameter should be a positive number and greater than 0")
            @RequestParam
            Integer size) throws Exception {
        return ResponseEntity.ok(importProductRepository.findAll(Pageable.ofSize(size).withPage(page)));
    }
}
