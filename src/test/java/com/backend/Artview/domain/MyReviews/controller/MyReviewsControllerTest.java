package com.backend.Artview.domain.MyReviews.controller;

import com.backend.Artview.domain.MyReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.service.MyReviewsService;
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
        int UserId = 10001;
        List<AllMyReviewsResponseDto> allMyReviews = myReviewsService.findAllMyReviews((long) UserId);
        assertThat(allMyReviews).isNotNull();
    }
}