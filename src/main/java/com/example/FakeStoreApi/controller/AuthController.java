package com.example.FakeStoreApi.controller;

import com.example.FakeStoreApi.dto.SignInRequestDto;
import com.example.FakeStoreApi.dto.SignInResponseDto;
import com.example.FakeStoreApi.dto.SignUpRequestDto;
import com.example.FakeStoreApi.dto.SignUpResponseDto;
import com.example.FakeStoreApi.entity.User;
import com.example.FakeStoreApi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok(authService.signin(signInRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {

        return ResponseEntity.ok(authService.signup(signUpRequestDto));
    }
}
