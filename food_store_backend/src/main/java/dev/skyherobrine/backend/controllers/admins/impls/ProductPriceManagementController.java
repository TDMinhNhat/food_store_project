package dev.skyherobrine.backend.controllers.admins.impls;

import dev.skyherobrine.backend.controllers.admins.IManagement;
import dev.skyherobrine.backend.dtos.ProductPriceDto;
import dev.skyherobrine.backend.keys.ProductPriceKey;
import dev.skyherobrine.backend.repositories.oracle.ProductPriceRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/product_price")
@Tag(name = "Product Price Management (Admin - Warehouse)", description = "Manage price of products in the warehouse")
@RequiredArgsConstructor
public class ProductPriceManagementController implements IManagement<ProductPriceDto, ProductPriceKey> {

    private final ProductPriceRepository productPriceRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<Object> add(ProductPriceDto productPriceDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> addMany(List<ProductPriceDto> list) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(ProductPriceKey productPriceKey, ProductPriceDto productPriceDto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(ProductPriceKey productPriceKey) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Object> getById(ProductPriceKey productPriceKey) throws Exception {
        return null;
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
