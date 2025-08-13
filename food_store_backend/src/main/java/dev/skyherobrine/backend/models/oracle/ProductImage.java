package dev.skyherobrine.backend.models.oracle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "ProductImages")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_id", length = 50, nullable = false, unique = true) @NonNull
    private String imageId;

    @ManyToOne @JoinColumn(name = "product_id", nullable = false) @NonNull
    private Product product;

    @Column(length = 300, nullable = false) @NonNull
    private String path;

    @Column(length = 100, nullable = false) @NonNull
    private String alternative;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.status = true;
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
