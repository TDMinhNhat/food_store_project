package dev.skyherobrine.backend.models.oracle;

import dev.skyherobrine.backend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "Orders")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "cust_id", nullable = false) @NonNull
    private User customer;

    @ManyToOne @JoinColumn(name = "emp_id", nullable = false) @NonNull
    private User employee;

    @ManyToOne @JoinColumn(name = "ship_id", nullable = false) @NonNull
    private User shipper;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.status = OrderStatus.WAIT_PAYMENT;
        this.orderDate = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
