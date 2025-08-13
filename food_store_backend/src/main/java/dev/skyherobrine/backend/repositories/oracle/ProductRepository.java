package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.Product;
import dev.skyherobrine.backend.projects.ProductProject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(String productId);

    @Query("select p from Product p")
    List<Product> getPageListProducts(Pageable pageable);

    List<Product> findAllByProductType_TypeName(String productTypeTypeName, Pageable pageable);
}
