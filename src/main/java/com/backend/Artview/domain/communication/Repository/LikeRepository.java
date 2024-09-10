package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByCommunicationsIdAndUsersId(Long communicationsId, Long userId);

    void deleteByCommunicationsIdAndUsersId(Long aLong, Long userId);
}
