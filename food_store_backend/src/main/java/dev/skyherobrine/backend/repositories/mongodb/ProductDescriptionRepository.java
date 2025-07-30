package dev.skyherobrine.backend.repositories.mongodb;

import dev.skyherobrine.backend.models.mongodb.ProductDescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDescriptionRepository extends MongoRepository<ProductDescription, String> {
}
