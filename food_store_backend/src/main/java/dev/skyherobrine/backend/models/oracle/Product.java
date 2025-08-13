package dev.skyherobrine.backend.models.oracle;

import dev.skyherobrine.backend.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "Products")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", length = 50, nullable = false, unique = true) @NonNull
    private String productId;

    @Column(name = "product_name", length = 200, nullable = false, unique = true) @NonNull
    private String productName;

    @Column(length = 100, nullable = false) @NonNull
    private String manufacturer;

    @Column(length = 100, nullable = false) @NonNull
    private String originCountry;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false) @NonNull
    private String unit;

    @ManyToOne @JoinColumn(name = "product_type_id", nullable = false) @NonNull
    private ProductType productType;

    @Enumerated(EnumType.ORDINAL) @Column(nullable = false)
    private ProductStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.quantity = 0.0;
        this.status = ProductStatus.IMPORTING;
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
