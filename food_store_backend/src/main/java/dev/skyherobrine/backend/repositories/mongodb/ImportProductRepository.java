package dev.skyherobrine.backend.repositories.mongodb;

import dev.skyherobrine.backend.models.mongodb.ImportProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportProductRepository extends MongoRepository<ImportProduct, Long> {
}
