package com.saurabh.finance_backend.controller;

import com.saurabh.finance_backend.dto.AuthResponse;
import com.saurabh.finance_backend.dto.RefreshRequest;
import com.saurabh.finance_backend.entity.User;
import com.saurabh.finance_backend.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {
        return authService.refresh(request);
    }
}