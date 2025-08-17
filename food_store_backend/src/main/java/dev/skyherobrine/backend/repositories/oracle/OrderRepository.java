package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ListCrudRepository<Order, Long>, ListPagingAndSortingRepository<Order, Long> {
}
