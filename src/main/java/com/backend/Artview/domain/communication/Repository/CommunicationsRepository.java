package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Communications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationsRepository extends JpaRepository<Communications,Long> {
}
