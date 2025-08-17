package dev.skyherobrine.backend.projects;

import dev.skyherobrine.backend.enums.OrderStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class OrderProject implements Serializable {
    private UserProject customer;
    private UserProject employee;
    private UserProject shipper;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private List<OrderDetailProject> orderDetails;
    private OrderStatus status;
}
