package dev.skyherobrine.backend.repositories.mongodb;

import dev.skyherobrine.backend.models.mongodb.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends MongoRepository<ProductReview, Long> {
    Optional<ProductReview> findFirstByStatusNotNullOrderByIdDesc();

    List<ProductReview> findByProductIdAndStatusTrue(String productId);

    List<ProductReview> findAllByProductId(String productId);
}
