package dev.skyherobrine.backend.models.oracle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "UserRoles")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class UserRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", length = 100, nullable = false, unique = true) @NonNull
    private String roleName;

    @Column(length = 300)
    private String description;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public UserRole(@NonNull String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        this.status = true;
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
