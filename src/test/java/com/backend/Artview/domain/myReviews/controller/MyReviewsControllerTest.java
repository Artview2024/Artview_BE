package com.backend.Artview.domain.myReviews.controller;

import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.service.MyReviewsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyReviewsControllerTest {

    private MyReviewsService myReviewsService;

    @Autowired
    public MyReviewsControllerTest(final MyReviewsService myReviewsService) {
        this.myReviewsService = myReviewsService;
    }

    @Test
    @DisplayName("유저 Id가 10001번인 회원의 Reviews 조회에 성공해야 한다.")
    void findAllMyReviews() {
        Long userId = 10001L;
        List<AllMyReviewsResponseDto> allMyReviews = myReviewsService.findAllMyReviews(userId);
        assertThat(allMyReviews).isNotNull();
    }

    @Test
    @DisplayName("ReviesId가 10001번인 리뷰의 조회에 성공해야 한다.")
    void findDetailMyReviews(){
        Long reviewsId = 10001L;
        DetailMyReviewsResponseDto detailMyReviews = myReviewsService.findDetailMyReviews(reviewsId);
        assertThat(detailMyReviews).isNotNull();
    }
}