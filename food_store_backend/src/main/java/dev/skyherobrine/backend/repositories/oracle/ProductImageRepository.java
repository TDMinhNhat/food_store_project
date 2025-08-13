package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.ProductImage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends ListCrudRepository<ProductImage, Long>, ListPagingAndSortingRepository<ProductImage, Long> {
}
