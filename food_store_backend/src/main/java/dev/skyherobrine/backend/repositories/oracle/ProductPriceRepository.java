package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.ProductPrice;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends ListCrudRepository<ProductPrice, Long>, ListPagingAndSortingRepository<ProductPrice, Long> {
}
