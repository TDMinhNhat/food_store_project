package dev.skyherobrine.backend.keys;

import dev.skyherobrine.backend.models.oracle.Order;
import dev.skyherobrine.backend.models.oracle.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class OrderDetailKey implements Serializable {
    @ManyToOne @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
