package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.ProductPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPriceRepository extends ListCrudRepository<ProductPrice, Long>, ListPagingAndSortingRepository<ProductPrice, Long> {
    @Query("select p from ProductPrice p where p.id.product.productId = ?1 order by p.id.priceDate DESC")
    List<ProductPrice> findById_Product_ProductIdOrderById_PriceDateDesc(String productId);
}
