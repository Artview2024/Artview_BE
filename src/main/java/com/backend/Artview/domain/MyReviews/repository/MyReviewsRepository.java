package com.backend.Artview.domain.MyReviews.repository;


import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyReviewsRepository extends JpaRepository<MyReviews,Long> {

    List<MyReviews> findAllByUsersId(Long userId);

    Optional<MyReviews> findByIdAndUsersId(Long reviewId, Long userId);
}
