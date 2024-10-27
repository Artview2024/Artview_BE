package com.backend.Artview.domain.myReviews.controller;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.TestDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.service.MyReviewsService;
import com.backend.Artview.global.customAnnotation.UserId;
import com.backend.Artview.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/myReviews")
@RequiredArgsConstructor
public class MyReviewsController {

    private final MyReviewsService myReviewsService;
    private final S3Util s3Util;

    //나의 전시 기록을 조회
    @GetMapping("/all")
    public List<AllMyReviewsResponseDto> findAllMyReviews(@UserId Long userId){
        return myReviewsService.findAllMyReviews(userId);
    }

    //전시 기록 상세 조회
    @GetMapping("/{reviewsId}")
    public DetailMyReviewsResponseDto findDetailMyReviews(@PathVariable Long reviewsId){
        return myReviewsService.findDetailMyReviews(reviewsId);
    }

    //전시 기록 작성하기(등록하기)
//    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public Long saveMyReviews(@RequestPart(value ="requestDto") MyReviewsSaveRequestDto requestDto,
//                              @RequestPart(value="mainImage") MultipartFile mainImage,
//                              @RequestPart(value = "contentImages") List<MultipartFile> contentImages
//    ) {
//        return myReviewsService.saveMyReviews(requestDto,mainImage,contentImages);
//    }

    //전시 기록 작성하기(등록하기)
    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Long saveMyReviews(@UserId Long userId, @ModelAttribute MyReviewsSaveRequestDto requestDto) {
        return myReviewsService.saveMyReviews(userId, requestDto);
    }


    //전시 기록 수정하기
//    @PatchMapping("/modify")
//    public void refactorMyReviews(@RequestPart MyReviewsModifyRequestDto requestDto,
//                                  @RequestPart(value="mainImage") MultipartFile mainImage,
//                                  @RequestPart(value = "contentImages") List<MultipartFile> contentImages){
//        myReviewsService.refactorMyReviews(requestDto,mainImage,contentImages);
//    }

    //전시 기록 수정하기
    @PatchMapping("/modify")
    public void refactorMyReviews(@UserId Long userId, @ModelAttribute MyReviewsModifyRequestDto requestDto){
        myReviewsService.refactorMyReviews(userId, requestDto);
    }

    //전시 기록 작성하기(등록하기)
    @PostMapping("/test")
    public String test(@ModelAttribute TestDto testDto) {
        return s3Util.uploadFileToS3Bucket(testDto.getMainImage());
    }
}