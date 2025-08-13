package dev.skyherobrine.backend.models.oracle;

import dev.skyherobrine.backend.keys.ProductPriceKey;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "ProductPrices")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductPrice {

    @EmbeddedId @NonNull
    private ProductPriceKey id;

    @Column(nullable = false) @NonNull
    private Double price;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
