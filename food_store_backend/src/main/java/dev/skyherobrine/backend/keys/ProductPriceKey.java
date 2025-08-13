package dev.skyherobrine.backend.keys;

import dev.skyherobrine.backend.models.oracle.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductPriceKey implements Serializable {

    @Column(name = "price_date", nullable = false)
    private LocalDateTime priceDate;

    @ManyToOne @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
