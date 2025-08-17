package dev.skyherobrine.backend.services;

import dev.skyherobrine.backend.models.mongodb.ProductDescription;
import dev.skyherobrine.backend.models.oracle.Product;
import dev.skyherobrine.backend.models.oracle.ProductPrice;
import dev.skyherobrine.backend.models.oracle.User;
import dev.skyherobrine.backend.projects.ProductProject;
import dev.skyherobrine.backend.projects.ProductReviewProject;
import dev.skyherobrine.backend.projects.UserProject;
import dev.skyherobrine.backend.repositories.mongodb.ProductDescriptionRepository;
import dev.skyherobrine.backend.repositories.mongodb.ProductReviewRepository;
import dev.skyherobrine.backend.repositories.oracle.*;
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
    private final ProductPriceRepository productPriceRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductDescriptionRepository productDescriptionRepository;
    private final ProductReviewRepository productReviewRepository;
    private final UserRepository userRepository;

    private Function<Product, ProductProject> processToProductProject() {
        return item -> {
            ProductProject result = new ProductProject();
            BeanUtils.copyProperties(item, result, "id");

            ProductPrice productPrice = productPriceRepository.findById_Product_ProductIdOrderById_PriceDateDesc(item.getProductId()).getFirst();
            result.setPrice(productPrice);

            result.setImages(productImageRepository.findAllByProduct_ProductId(item.getProductId()));

            result.setDescriptions(productDescriptionRepository.findById(item.getProductId()).orElse(new ProductDescription()).getDescriptions());

            result.setReviews(productReviewRepository.findByProductIdAndStatusTrue(item.getProductId()).parallelStream().map(productReview -> {
                ProductReviewProject review = new ProductReviewProject();
                BeanUtils.copyProperties(productReview, review, "id", "status");

                User userReview = userRepository.findByUserId(productReview.getUserId()).orElseThrow(() -> new EntityNotFoundException("The user id with " + productReview.getUserId() + " couldn't find review this product"));
                UserProject userProject = new UserProject();
                BeanUtils.copyProperties(userReview, userProject, "id", "address", "password", "updatedAt");
                review.setUser(userProject);

                return review;
            }).toList());
            return result;
        };
    }

    public List<ProductProject> getAllProductsPage(int page, int size) {
        return productRepository.findAll(Pageable.ofSize(size).withPage(page)).stream().parallel().map(
                processToProductProject()
        ).toList();
    }

    public List<ProductProject> getAllProductsSort(Map<String, String> sorting) {
        List<Sort.Order> orders = new ArrayList<>();
        sorting.forEach((key, value) -> {
            orders.add(new Sort.Order(Sort.Direction.valueOf(value), key));
        });
        return productRepository.findAll(Sort.by(orders)).parallelStream().map(
                processToProductProject()
        ).toList();
    }

    public List<ProductProject> getAllProductsByType(String type, Integer page, Integer size) {
        return productRepository.findAllByProductType_TypeName(type, Pageable.ofSize(size).withPage(page)).parallelStream().map(
                processToProductProject()
        ).toList();
    }
}
