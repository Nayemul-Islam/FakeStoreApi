package com.example.FakeStoreApi.controller;

import com.example.FakeStoreApi.dto.ProductRequestDto;
import com.example.FakeStoreApi.dto.ProductResponseDto;
import com.example.FakeStoreApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    /// Get Products Endpoint
    @GetMapping("/")
    public ResponseEntity<Page<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "none") String sortBy
    ) {
        return ResponseEntity.ok(productService.getProducts(page, size, category, search, sortBy));
    }


    /// Get Product by ID Endpoint
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /// Add Product Endpoint
    @PostMapping("/")
    public ResponseEntity<ProductResponseDto> addProducts(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProducts(productRequestDto));
    }

    /// Update Product Endpoint
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        return ResponseEntity.ok(productService.deleteById(id));
    }
}
