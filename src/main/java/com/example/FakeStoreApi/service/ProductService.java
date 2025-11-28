package com.example.FakeStoreApi.service;

import com.example.FakeStoreApi.dto.ProductRequestDto;
import com.example.FakeStoreApi.dto.ProductResponseDto;
import com.example.FakeStoreApi.entity.Product;
import com.example.FakeStoreApi.repository.ProductRepository;
import com.example.FakeStoreApi.utility.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /// Get all Product function
    public Page<ProductResponseDto> getProducts(
            int page,
            int size,
            String category,
            String search,
            String sortBy
    ) {

        Sort sort = switch (sortBy.toLowerCase()) {
            case "price_asc" -> Sort.by("price").ascending();
            case "price_desc" -> Sort.by("price").descending();
            case "name_asc" -> Sort.by("title").ascending();
            case "name_desc" -> Sort.by("title").descending();
            default -> Sort.unsorted();
        };   /// Filter Condition

        Pageable pageable = PageRequest.of(page, size, sort);

        if(category == null) category = "";
        if(search == null) search = "";

        Page<Product> products =
                productRepository.findByCategoryContainingIgnoreCaseAndTitleContainingIgnoreCase(
                        category, search, pageable
                );

        return products.map(productMapper::toResponseDto);
    }


    /// Get Product by ID Function
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

    /// Update Product Function
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id) {

        Product product = productMapper.toEntity(productRequestDto);
        product.setId(id);
        productRepository.save(product);
        return productMapper.toResponseDto(product);
    }


    public Void deleteById(Long id) {
        productRepository.deleteById(id);
        return null;
    }
}
