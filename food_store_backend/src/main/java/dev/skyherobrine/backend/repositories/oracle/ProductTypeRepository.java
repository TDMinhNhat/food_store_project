package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.ProductType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends ListCrudRepository<ProductType, Long> {
}
