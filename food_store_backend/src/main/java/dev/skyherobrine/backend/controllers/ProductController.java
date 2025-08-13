package dev.skyherobrine.backend.controllers;

import dev.skyherobrine.backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/page")
    public ResponseEntity<Object> getAllProductsPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(productService.getAllProductsPage(page, size));
    }

    @GetMapping("/sort")
    public ResponseEntity<Object> getAllProductsSort(
            @RequestBody Map<String,String> sorting
    ) {
        return ResponseEntity.ok(productService.getAllProductsSort(sorting));
    }

    @GetMapping("/type/page")
    public ResponseEntity<Object> getAllProductsByType(
            @RequestParam String type,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(productService.getAllProductsByType(type, page, size));
    }
}
