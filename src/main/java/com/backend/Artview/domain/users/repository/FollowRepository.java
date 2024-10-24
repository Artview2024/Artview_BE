package com.backend.Artview.domain.users.repository;

import com.backend.Artview.domain.users.domain.Follow;
import com.backend.Artview.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    boolean existsByGiveFollowUsersAndTakeFollowUsers(Users giveFollowUser, Users takeFollowUser);

    void deleteByGiveFollowUsersAndTakeFollowUsers(Users giveUnFollowUser, Users takeUnFollowUser);

    int countByGiveFollowUsers(Users giveFollowUsers);

    int countByTakeFollowUsers(Users takeFollowUsers);

    //내가 팔로우 하는 리스트
    @Query("SELECT f.takeFollowUsers FROM Follow f WHERE f.giveFollowUsers = :users")
    List<Users> findMyFollowingList(@Param("users") Users users);

    //나를 팔로우하는 리스트
    @Query("SELECT f.giveFollowUsers FROM Follow f WHERE  f.takeFollowUsers = :users")
    List<Users> findMyFollowerList(@Param("users") Users users);

}
