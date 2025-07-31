package dev.skyherobrine.backend.services.admins;

import dev.skyherobrine.backend.dtos.ProductDto;
import dev.skyherobrine.backend.models.oracle.Product;
import dev.skyherobrine.backend.models.oracle.ProductType;
import dev.skyherobrine.backend.repositories.mongodb.ImportProductRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductPriceRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductManagementService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ImportProductRepository importProductRepository;

    public Product addProduct(ProductDto dto) {
        ProductType productType = productTypeRepository.findByTypeName(dto.productTypeName()).orElseThrow(() ->
                new EntityNotFoundException("Product type not found: " + dto.productTypeName()));

        Product product = new Product(
                generateProductId(),
                dto.productName(),
                dto.manufacturer(),
                dto.originCountry(),
                dto.unit(),
                productType
        );

        return productRepository.save(product);
    }

    private String generateProductId() {
        return "";
    }

    public Product updateProduct(String productId, ProductDto dto) {
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new EntityNotFoundException("The product id with " + productId + " does not exist"));
        ProductType productType = productTypeRepository.findByTypeName(dto.productTypeName()).orElseThrow(() -> new EntityNotFoundException("Product type not found: " + dto.productTypeName()));

        product.setProductName(dto.productName());
        product.setManufacturer(dto.manufacturer());
        product.setOriginCountry(dto.originCountry());
        product.setUnit(dto.unit());
        product.setProductType(productType);

        return productRepository.save(product);
    }
}
