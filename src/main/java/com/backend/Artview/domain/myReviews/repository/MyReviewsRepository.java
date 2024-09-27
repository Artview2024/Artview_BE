package com.backend.Artview.domain.myReviews.repository;


import com.backend.Artview.domain.myReviews.domain.MyReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyReviewsRepository extends JpaRepository<MyReviews,Long> {

    List<MyReviews> findAllByUsersId(Long userId);

    Optional<MyReviews> findByIdAndUsersId(Long reviewId, Long userId);

    @Query("SELECT COUNT(*) FROM MyReviews mr WHERE mr.users.id = :userId")
    int countMyReview(Long userId);
}
