package com.saurabh.finance_backend.service;

import com.saurabh.finance_backend.dto.*;
import com.saurabh.finance_backend.entity.*;
import com.saurabh.finance_backend.repository.*;
import com.saurabh.finance_backend.service.RefreshTokenService;

import com.saurabh.finance_backend.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshService;
    private final RefreshTokenRepository refreshRepo;

    public String register(User user) {

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(true);

        userRepo.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(User user) {

        User dbUser = userRepo.findByUsername(user.getUsername())
                .orElseThrow();

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String access = jwtUtil.generateToken(
                dbUser.getUsername(),
                dbUser.getRole().name()
        );

        RefreshToken refresh = refreshService.createRefreshToken(dbUser.getUsername());

        AuthResponse res = new AuthResponse();
        res.setAccessToken(access);
        res.setRefreshToken(refresh.getToken());

        return res;
    }

    public AuthResponse refresh(RefreshRequest request) {

        RefreshToken token = refreshRepo.findByToken(request.getRefreshToken())
                .orElseThrow();

        refreshService.verifyExpiration(token);

        String access = jwtUtil.generateToken(
                token.getUser().getUsername(),
                token.getUser().getRole().name()
        );

        AuthResponse res = new AuthResponse();
        res.setAccessToken(access);
        res.setRefreshToken(token.getToken());

        return res;
    }
}