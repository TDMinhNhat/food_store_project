package dev.skyherobrine.backend.services.admins;

import dev.skyherobrine.backend.dtos.ImportProductDto;
import dev.skyherobrine.backend.models.mongodb.ImportProduct;
import dev.skyherobrine.backend.models.oracle.Product;
import dev.skyherobrine.backend.repositories.mongodb.ImportProductRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportProductManagementService {

    private final ImportProductRepository importProductRepository;
    private final ProductRepository productRepository;

    public ImportProduct addImportProduct(ImportProductDto dto) {
        Product product = productRepository.findByProductId(dto.productId()).orElseThrow(() -> new EntityNotFoundException("The productId with id " + dto.productId() + " does not exist"));

        //Update quantity of the product
        product.setQuantity(product.getQuantity() + dto.quantity());
        productRepository.save(product);

        ImportProduct importProduct = new ImportProduct(
                getMaxId() + 1,
                product.getProductId(),
                dto.supplyName(),
                dto.quantity()
        );

        return importProductRepository.save(importProduct);
    }

    private Long getMaxId() {
        List<ImportProduct> list = importProductRepository.findAll(Sort.by("id").descending());
        return !list.isEmpty() ? list.getFirst().getId() : 0L;
    }
}
