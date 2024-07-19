package com.backend.Artview.domain.MyReviews.controller;

import com.backend.Artview.domain.MyReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.service.MyReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/myReviews")
@RequiredArgsConstructor
public class MyReviewsController {

    private final MyReviewsService myReviewsService;

    //나의 전시 기록을 조회
    @GetMapping("/all/{userId}")
    public List<AllMyReviewsResponseDto> findAllMyReviews(@PathVariable Long userId){
        return myReviewsService.findAllMyReviews(userId);
    }

    //전시 기록 상세 조회
    @GetMapping("/{reviewsId}")
    public DetailMyReviewsResponseDto findDetailMyReviews(@PathVariable Long reviewsId){
        return myReviewsService.findDetailMyReviews(reviewsId);
    }

}
