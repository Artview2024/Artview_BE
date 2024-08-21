package com.backend.Artview.domain.myReviews.service;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveReqeustDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;

import java.util.List;


public interface MyReviewsService {

    List<AllMyReviewsResponseDto> findAllMyReviews(Long userId);

    DetailMyReviewsResponseDto findDetailMyReviews(Long reviewsId);

    Long saveMyReviews(MyReviewsSaveReqeustDto requestDto);

    void refactorMyReviews(MyReviewsModifyRequestDto requestDto);
}
