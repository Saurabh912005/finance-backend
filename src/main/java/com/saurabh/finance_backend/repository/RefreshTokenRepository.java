package com.saurabh.finance_backend.repository;

import com.saurabh.finance_backend.entity.RefreshToken;
import com.saurabh.finance_backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user);


    @Transactional
    @Modifying
    void deleteByUser(User user);
}