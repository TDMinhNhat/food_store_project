package dev.skyherobrine.backend.models.oracle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "Addresses")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number", length = 30, nullable = false) @NonNull
    private String houseNumber;

    @Column(length = 50, nullable = false) @NonNull
    private String street;

    @Column(length = 50, nullable = false) @NonNull
    private String ward;

    @Column(length = 50, nullable = false) @NonNull
    private String city;

    @Column(length = 50, nullable = false) @NonNull
    private String country;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
