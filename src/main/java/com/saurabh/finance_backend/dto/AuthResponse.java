package com.saurabh.finance_backend.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
}