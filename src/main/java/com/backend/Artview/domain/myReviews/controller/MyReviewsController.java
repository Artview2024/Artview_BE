package com.backend.Artview.domain.myReviews.controller;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveReqeustDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.service.MyReviewsService;
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

    //전시 기록 작성하기(등록하기)
    @PostMapping("/save")
    public Long saveMyReviews(@RequestBody MyReviewsSaveReqeustDto requestDto) {
        return myReviewsService.saveMyReviews(requestDto);
    }


    //전시 기록 수정하기
    @PatchMapping("/modify")
    public void refactorMyReviews(@RequestBody MyReviewsModifyRequestDto requestDto){
        myReviewsService.refactorMyReviews(requestDto);
    }

}