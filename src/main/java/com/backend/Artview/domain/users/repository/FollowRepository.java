package com.backend.Artview.domain.users.repository;

import com.backend.Artview.domain.users.domain.Follow;
import com.backend.Artview.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    boolean existsByFollowerAndFollowed(Users follower, Users followed);
}
