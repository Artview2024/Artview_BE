package com.backend.Artview.domain.test.repository;

import com.backend.Artview.domain.test.domain.Tests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TestRepository extends JpaRepository<Tests,Long> {

}
