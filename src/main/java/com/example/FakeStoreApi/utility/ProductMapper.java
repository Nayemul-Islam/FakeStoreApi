package com.example.FakeStoreApi.utility;

import com.example.FakeStoreApi.dto.ProductRequestDto;
import com.example.FakeStoreApi.dto.ProductResponseDto;
import com.example.FakeStoreApi.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDto dto);

    ProductResponseDto toResponseDto(Product product);
}
