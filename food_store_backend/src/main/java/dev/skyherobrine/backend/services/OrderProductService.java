package dev.skyherobrine.backend.services;

import dev.skyherobrine.backend.dtos.OrderDto;
import dev.skyherobrine.backend.enums.OrderStatus;
import dev.skyherobrine.backend.keys.OrderDetailKey;
import dev.skyherobrine.backend.models.oracle.Order;
import dev.skyherobrine.backend.models.oracle.OrderDetail;
import dev.skyherobrine.backend.models.oracle.User;
import dev.skyherobrine.backend.projects.OrderDetailProject;
import dev.skyherobrine.backend.projects.OrderProject;
import dev.skyherobrine.backend.projects.UserProject;
import dev.skyherobrine.backend.repositories.oracle.OrderDetailRepository;
import dev.skyherobrine.backend.repositories.oracle.OrderRepository;
import dev.skyherobrine.backend.repositories.oracle.ProductRepository;
import dev.skyherobrine.backend.repositories.oracle.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private String generateOrderId() {
        return "";
    }

    private OrderProject processToOrderProject(Order order, List<OrderDetail> orderDetails) {
        UserProject customerProject = new UserProject();
        UserProject employeeProject = new UserProject();

        return new OrderProject(
                customerProject, employeeProject, null,
                orderDetails.stream().mapToDouble(item -> item.getId().getProduct().getUpdatestPrice()).sum()
                , order.getOrderDate(),
                orderDetails.stream().map(item -> new OrderDetailProject(item.getId().getProduct(), item.getQuantity())).toList()
                , order.getStatus()
        );
    }

    public OrderProject makeOrder(OrderDto dto) {
        User customer = userRepository.findByUserId(dto.customerId()).orElseThrow(() -> new EntityNotFoundException("The customer with user id " + dto.customerId() + " wasn't found."));
        User employee = userRepository.findByUserId(dto.employeeId()).orElse(null);
        Order order = new Order(generateOrderId(), customer, employee);
        Order target = orderRepository.save(order);

        List<OrderDetail> orderDetails = orderDetailRepository.saveAll(dto.orderDetails().stream().map(item ->
                new OrderDetail(new OrderDetailKey(
                        target,
                        productRepository.findByProductId(item.productId()).orElseThrow(() -> new EntityNotFoundException("The product id " + item.productId() + " wasn't found."))
                ))
        ).toList());

        return processToOrderProject(target, orderDetails);
    }

    public OrderProject changeOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("The order id " + orderId + " wasn't found."));
        order.setStatus(OrderStatus.valueOf(status));
        Order result = orderRepository.save(order);
        List<OrderDetail> orderDetails = orderDetailRepository.findAllById_Order_OrderId(result.getOrderId());
        return processToOrderProject(result, orderDetails);
    }

    public OrderProject updateShipper(Long orderId, String shipperId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("The order id " + orderId + " wasn't found."));
        User shipper = userRepository.findByUserId(shipperId).orElseThrow(() -> new EntityNotFoundException("The shipper id " + shipperId + " wasn't found."));
        order.setShipper(shipper);
        Order result = orderRepository.save(order);
        List<OrderDetail> orderDetails = orderDetailRepository.findAllById_Order_OrderId(result.getOrderId());
        return processToOrderProject(result, orderDetails);
    }
}
