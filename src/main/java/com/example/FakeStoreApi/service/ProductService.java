package com.example.FakeStoreApi.service;

import com.example.FakeStoreApi.dto.ProductRequestDto;
import com.example.FakeStoreApi.dto.ProductResponseDto;
import com.example.FakeStoreApi.entity.Product;
import com.example.FakeStoreApi.repository.ProductRepository;
import com.example.FakeStoreApi.utility.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /// Get all Product function
    public Page<ProductResponseDto> getProducts(int page, int size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));

        return productPage.map(productMapper::toResponseDto);
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with this id: " + id));
        return productMapper.toResponseDto(product);
    }


    /// Add Product Function. Convert Product Request Dto to Product then save, and return Product Response Dto
    public ProductResponseDto addProducts(ProductRequestDto productRequestDto) {

        Product product = productRepository.save(productMapper.toEntity(productRequestDto));
        return productMapper.toResponseDto(product);
    }


}
