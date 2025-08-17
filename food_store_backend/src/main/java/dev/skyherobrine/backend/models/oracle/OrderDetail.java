package dev.skyherobrine.backend.models.oracle;

import dev.skyherobrine.backend.keys.OrderDetailKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Comparator;

@Entity @Table(name = "OrderDetails")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class OrderDetail {
    @EmbeddedId @NonNull
    private OrderDetailKey id;

    @Column(nullable = false)
    private Double quantity;

    public Double getPrice() {
        return quantity * id.getProduct().getPrices()
                .stream()
                .sorted(Comparator.comparing(o -> o.getId().getPriceDate()))
                .toList()
                .getLast()
                .getPrice();
    }
}
