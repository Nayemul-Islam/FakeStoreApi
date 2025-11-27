package com.example.FakeStoreApi.utility;

import com.example.FakeStoreApi.dto.SignUpRequestDto;
import com.example.FakeStoreApi.dto.SignUpResponseDto;
import com.example.FakeStoreApi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignUpRequestDto signUpRequestDto);
    SignUpResponseDto toResponseDto(User user);
}
