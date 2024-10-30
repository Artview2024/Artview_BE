package com.backend.Artview.domain.users.repository;

import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.domain.UsersInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersInterestRepository extends JpaRepository<UsersInterest,Long> {
    void deleteAllByUsers(Users users);

    boolean existsByUsers(Users users);
}
