package com.backend.Artview.domain.users.repository;

import com.backend.Artview.domain.users.domain.Users;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByKakaoId(Long kakaoId);

    Optional<Users> findByKakaoId(Long kakaoId);

    // 특정 관심 분야를 가진, 내가 팔로우하지 않은 사람을 조회
    @Query("SELECT u FROM Users u JOIN UsersInterest ui ON u.id = ui.users.id " +
            "WHERE u.id NOT IN (SELECT f.takeFollowUsers.id FROM Follow f WHERE f.giveFollowUsers.id = :userId) " +
            "AND u.id != :userId " +
            "AND ui.usersInterestContent IN :interests")
    List<Users> findRecommendUsersNotFollowAndSameInterests(@Param("userId") Long userId, @Param("interests") List<String> interests,  PageRequest pageable);

    // 랜덤 조회 쿼리 (팔로우하지 않은 사람 중 랜덤으로 가져오기)
    @Query("SELECT distinct u FROM Users u " +
            "WHERE u.id NOT IN (SELECT f.takeFollowUsers.id FROM Follow f WHERE f.giveFollowUsers.id = :userId) " +
            "AND u.id != :userId " +
            "AND u.id NOT IN :selectRecommendUsersId")
    List<Users> findRandomUsersNotFollowed(@Param("userId")Long userId, PageRequest pageRequest, List<Long> selectRecommendUsersId);
}
