package com.backend.Artview.domain.myReviews.service;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewExhibitionInfoResDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveRequestDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsMainResDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;

import java.util.List;


public interface MyReviewsService {

    List<AllMyReviewsMainResDto> findMainPageMyReviews(Long userId);

    List<AllMyReviewsResponseDto> findAllMyReviews(Long userId);

    DetailMyReviewsResponseDto findDetailMyReviews(Long reviewsId);

    Long saveMyReviews(Long userId, MyReviewsSaveRequestDto requestDto);

    //    void refactorMyReviews(MyReviewsModifyRequestDto requestDto, MultipartFile mainImage, List<MultipartFile> contentImages);
    void refactorMyReviews(Long userId, MyReviewsModifyRequestDto requestDto);

    List<MyReviewExhibitionInfoResDto> findExhibitionTitleByKeyword(String keyword);

    MyReviewExhibitionInfoResDto findExhibitionLocationByKeyword(Long exhibitionId);


}
