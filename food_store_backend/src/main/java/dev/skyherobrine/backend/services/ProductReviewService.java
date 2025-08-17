package dev.skyherobrine.backend.services;

import dev.skyherobrine.backend.dtos.ProductReviewDto;
import dev.skyherobrine.backend.models.mongodb.ProductReview;
import dev.skyherobrine.backend.projects.ProductReviewProject;
import dev.skyherobrine.backend.repositories.mongodb.ProductReviewRepository;
import dev.skyherobrine.backend.repositories.oracle.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final UserRepository userRepository;
    private final ProductReviewRepository productReviewRepository;

    private Long getMaxProductReviewId() {
        ProductReview result = productReviewRepository.findFirstByStatusNotNullOrderByIdDesc().orElse(null);
        return result == null ? 1L : result.getId() + 1;
    }

    public ProductReview addReview(ProductReviewDto dto) {
        ProductReview productReview = new ProductReview(
                getMaxProductReviewId(),
                dto.productId(),
                dto.userId(),
                dto.rating(),
                dto.comment()
        );
        return productReviewRepository.save(productReview);
    }

    public ProductReview updateReview(Long reviewId, ProductReviewDto dto) {
        ProductReview productReview = productReviewRepository.findById(reviewId).orElse(new ProductReview(
                getMaxProductReviewId(),
                dto.productId(),
                dto.userId(),
                dto.rating(),
                dto.comment()
        ));

        productReview.setRating(dto.rating());
        productReview.setComment(dto.comment());
        productReview.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return productReviewRepository.save(productReview);
    }

    public ProductReview dropComment(Long reviewId) {
        ProductReview productReview = productReviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("The review wasn't found in this product"));
        productReview.setStatus(false);
        return productReviewRepository.save(productReview);
    }
}
