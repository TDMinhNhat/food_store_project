package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.ProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends ListCrudRepository<ProductType, Long> {
    @Query("select p from ProductType p")
    List<ProductType> getPageListProductType(Pageable pageable);

    Optional<ProductType> findByTypeName(String typeName);
}
