package com.backend.Artview.domain.auth.repository;

import com.backend.Artview.domain.auth.domain.RefreshToken;
import com.backend.Artview.domain.users.domain.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByRefreshTokenAndUsersId(String refreshToken, Long userId);

    Optional<RefreshToken> findByUsersId(Long userId);

    @Query("DELETE FROM RefreshToken WHERE users.id = :userId")
    int deleteByUsersId(@Param("userId") Long userId);

    void deleteByRefreshToken(String refreshToken);

//    void deleteByUsers(Users users);
}
