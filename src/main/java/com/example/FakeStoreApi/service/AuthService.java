package com.example.FakeStoreApi.service;

import com.example.FakeStoreApi.dto.SignUpRequestDto;
import com.example.FakeStoreApi.dto.SignUpResponseDto;
import com.example.FakeStoreApi.entity.User;
import com.example.FakeStoreApi.repository.UserRepository;
import com.example.FakeStoreApi.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) throws Exception {
        User user = userMapper.toEntity(signUpRequestDto);
        user = userRepository.save(user);
        return userMapper.toResponseDto(user);
    }
}
