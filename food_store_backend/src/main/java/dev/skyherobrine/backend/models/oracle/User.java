package dev.skyherobrine.backend.models.oracle;

import dev.skyherobrine.backend.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "Users")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 30, unique = true, nullable = false) @NonNull
    private String userId;

    @Column(name = "first_name", length = 50, nullable = false) @NonNull
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false) @NonNull
    private String lastName;

    @Column(nullable = false) @NonNull
    private Boolean sex;

    @Column(name = "birth_date", nullable = false) @NonNull
    private LocalDate birthDate;

    @Column(name = "phone_number", length = 25, unique = true, nullable = false) @NonNull
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    private String image;

    @Column(length = 200, unique = true, nullable = false) @NonNull
    private String email;

    @Column(length = 200, nullable = false) @NonNull
    private String password;

    @ManyToOne @JoinColumn(name = "role_id", nullable = false) @NonNull
    private UserRole role;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public User(@NonNull String userId, @NonNull String firstName, @NonNull String lastName, @NonNull Boolean sex, @NonNull LocalDate birthDate, @NonNull String phoneNumber, Address address, @NonNull String password, @NonNull String email, @NonNull UserRole role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @PrePersist
    public void onPrePersist() {
        this.status = UserStatus.ACTIVE;
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
