package com.saurabh.finance_backend.service;

import com.saurabh.finance_backend.entity.RefreshToken;
import com.saurabh.finance_backend.entity.User;
import com.saurabh.finance_backend.repository.RefreshTokenRepository;
import com.saurabh.finance_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final UserRepository userRepo;

    private final long REFRESH_DURATION = 7 * 24 * 60 * 60 * 1000;

    @Transactional
    public RefreshToken createRefreshToken(String username) {

        User user = userRepo.findByUsername(username).orElseThrow();

        // Try existing token
        Optional<RefreshToken> existing = repo.findByUser(user);

        if (existing.isPresent()) {
            RefreshToken token = existing.get();
            token.setToken(UUID.randomUUID().toString());
            token.setExpiryDate(Instant.now().plusMillis(REFRESH_DURATION));
            return repo.save(token);
        }

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusMillis(REFRESH_DURATION));

        return repo.save(token);
    }

    @Transactional  // ✅ IMPORTANT
    public void verifyExpiration(RefreshToken token) {

        if (token.getExpiryDate().isBefore(Instant.now())) {
            repo.delete(token);
            throw new RuntimeException("Refresh token expired");
        }
    }
}