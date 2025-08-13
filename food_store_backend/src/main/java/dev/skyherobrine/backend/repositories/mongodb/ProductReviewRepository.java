package dev.skyherobrine.backend.repositories.mongodb;

import dev.skyherobrine.backend.models.mongodb.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends MongoRepository<ProductReview, Long> {
}
