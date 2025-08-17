package dev.skyherobrine.backend.controllers;

import dev.skyherobrine.backend.dtos.OrderDto;
import dev.skyherobrine.backend.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order_product")
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService orderProductService;

    @PostMapping
    public ResponseEntity<Object> makeOrder(OrderDto dto) {
        return ResponseEntity.ok(orderProductService.makeOrder(dto));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Object> changeOrderStatus(
            @PathVariable("orderId") Long orderId,
            @RequestParam String status) {
        return ResponseEntity.ok(orderProductService.changeOrderStatus(orderId, status));
    }

    @PutMapping("/{orderId}/shipper")
    public ResponseEntity<Object> updateShipper(
            @PathVariable("orderId") Long orderId,
            @RequestParam String shiperId) {
        return ResponseEntity.ok(orderProductService.updateShipper(orderId, shiperId));
    }
}
