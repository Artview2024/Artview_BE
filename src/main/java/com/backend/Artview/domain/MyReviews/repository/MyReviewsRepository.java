package com.backend.Artview.domain.MyReviews.repository;


import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyReviewsRepository extends JpaRepository<MyReviews,Long> {

    List<MyReviews> findAllByUsersId(Long userId);
}
