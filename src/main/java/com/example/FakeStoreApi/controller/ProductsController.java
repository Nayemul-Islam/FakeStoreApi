package com.example.FakeStoreApi.controller;

import com.example.FakeStoreApi.dto.ProductRequestDto;
import com.example.FakeStoreApi.dto.ProductResponseDto;
import com.example.FakeStoreApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    /// Get All products Endpoint
    @GetMapping("/")
    public ResponseEntity<Page<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(productService.getProducts(page, size));
    }

    /// Get Product By ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }


    @PostMapping("/")
    public ResponseEntity<ProductResponseDto> addProducts(@RequestBody ProductRequestDto productRequestDto) {
        System.out.println(productRequestDto);
        return ResponseEntity.ok(productService.addProducts(productRequestDto));
    }
}
