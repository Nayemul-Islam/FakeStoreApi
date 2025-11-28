package com.example.FakeStoreApi.service;

import com.example.FakeStoreApi.dto.SignInRequestDto;
import com.example.FakeStoreApi.dto.SignInResponseDto;
import com.example.FakeStoreApi.dto.SignUpRequestDto;
import com.example.FakeStoreApi.dto.SignUpResponseDto;
import com.example.FakeStoreApi.entity.User;
import com.example.FakeStoreApi.repository.UserRepository;
import com.example.FakeStoreApi.security.AuthUtil;
import com.example.FakeStoreApi.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) throws Exception {
        User user = userMapper.toEntity(signUpRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toResponseDto(userRepository.save(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public SignInResponseDto signin(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getUsernameOrEmail(), signInRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.getJwtToken(user);

        return new SignInResponseDto(token, user.getId().toString());
    }
}
