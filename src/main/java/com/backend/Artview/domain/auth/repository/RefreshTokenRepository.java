package com.backend.Artview.domain.auth.repository;

import com.backend.Artview.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByRefreshTokenAndUsersId(String refreshToken, Long userId);

    Optional<RefreshToken> findByUsersId(Long userId);
}
