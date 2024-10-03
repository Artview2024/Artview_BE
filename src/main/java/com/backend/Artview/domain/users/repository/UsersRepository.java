package com.backend.Artview.domain.users.repository;

import com.backend.Artview.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByKakaoId(Long kakaoId);

    Optional<Users> findByKakaoId(Long kakaoId);
}
