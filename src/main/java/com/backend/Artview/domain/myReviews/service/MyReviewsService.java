package com.backend.Artview.domain.myReviews.service;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveRequestDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface MyReviewsService {

    List<AllMyReviewsResponseDto> findAllMyReviews(Long userId);

    DetailMyReviewsResponseDto findDetailMyReviews(Long reviewsId);

//    Long saveMyReviews(MyReviewsSaveRequestDto requestDto, MultipartFile mainImage, List<MultipartFile> contentImages);

    Long saveMyReviews(MyReviewsSaveRequestDto requestDto);

    void refactorMyReviews(MyReviewsModifyRequestDto requestDto, MultipartFile mainImage, List<MultipartFile> contentImages);
}
