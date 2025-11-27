package com.example.FakeStoreApi.controller;

import com.example.FakeStoreApi.dto.SignUpRequestDto;
import com.example.FakeStoreApi.dto.SignUpResponseDto;
import com.example.FakeStoreApi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {

        return ResponseEntity.ok(authService.signup(signUpRequestDto));
    }
}
