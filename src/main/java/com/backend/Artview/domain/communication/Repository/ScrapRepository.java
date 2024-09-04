package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Like;
import com.backend.Artview.domain.communication.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap,Long> {
    boolean existsByCommunicationsIdAndUsersId(Long communicationsId, Long userId);
}
