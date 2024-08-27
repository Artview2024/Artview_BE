package com.backend.Artview.domain.myReviews.controller;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveRequestDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.service.MyReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Long saveMyReviews(@RequestPart(value ="requestDto") MyReviewsSaveRequestDto requestDto,
                              @RequestPart(value="mainImage") MultipartFile mainImage,
                              @RequestPart(value = "contentImages") List<MultipartFile> contentImages
    ) {
        return myReviewsService.saveMyReviews(requestDto,mainImage,contentImages);
    }


    //전시 기록 수정하기
    @PatchMapping("/modify")
    public void refactorMyReviews(@RequestPart MyReviewsModifyRequestDto requestDto,
                                  @RequestPart(value="mainImage") MultipartFile mainImage,
                                  @RequestPart(value = "contentImages") List<MultipartFile> contentImages){
        myReviewsService.refactorMyReviews(requestDto,mainImage,contentImages);
    }

    @PatchMapping("/test")
    public String test(@RequestPart MyReviewsSaveRequestDto dto){
        return myReviewsService.test(dto);
    }

}