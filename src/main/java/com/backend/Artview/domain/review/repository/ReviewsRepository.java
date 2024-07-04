package com.backend.Artview.domain.review.repository;

import com.backend.Artview.domain.review.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {

}
