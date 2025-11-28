package com.example.FakeStoreApi.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SignInRequestDto {
    private String usernameOrEmail;
    private String password;
}
