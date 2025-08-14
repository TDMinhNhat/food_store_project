package dev.skyherobrine.backend.services;

import dev.skyherobrine.backend.models.mongodb.ProductDescription;
import dev.skyherobrine.backend.models.oracle.Product;
import dev.skyherobrine.backend.models.oracle.ProductPrice;
import dev.skyherobrine.backend.projects.ProductProject;
import dev.skyherobrine.backend.repositories.mongodb.ProductDescriptionRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductImageRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductPriceRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductDescriptionRepository productDescriptionRepository;

    private Function<Product, ProductProject> processToProductProject() {
        return item -> {
            ProductProject result = new ProductProject();
            BeanUtils.copyProperties(item, result, "id");

            ProductPrice productPrice = productPriceRepository.findById_Product_ProductIdOrderById_PriceDateDesc(item.getProductId()).getFirst();
            result.setPrice(productPrice);

            result.setImages(productImageRepository.findAllByProduct_ProductId(item.getProductId()));
            return result;
        };
    }

    public List<ProductProject> getAllProductsPage(int page, int size) {
        return productRepository.findAll(Pageable.ofSize(size).withPage(page)).map(
                processToProductProject()
        ).stream().toList();
    }

    public List<ProductProject> getAllProductsSort(Map<String, String> sorting) {
        List<Sort.Order> orders = new ArrayList<>();
        sorting.forEach((key, value) -> {
            orders.add(new Sort.Order(Sort.Direction.valueOf(value), key));
        });
        return productRepository.findAll(Sort.by(orders)).stream().map(
                processToProductProject()
        ).toList();
    }

    public List<ProductProject> getAllProductsByType(String type, Integer page, Integer size) {
        return productRepository.findAllByProductType_TypeName(type, Pageable.ofSize(size).withPage(page)).stream().map(
                processToProductProject()
        ).toList();
    }

    public ProductDescription getProductDescription(String productId) {
        return productDescriptionRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("The product id with " + productId + " wasn't found!"));
    }
}
