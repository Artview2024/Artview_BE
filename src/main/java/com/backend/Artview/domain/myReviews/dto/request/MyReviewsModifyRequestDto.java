package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class MyReviewsModifyRequestDto<T>{

        Long myReviewsId;
        String name;
        String date;
        String gallery;
        T mainImage;
//        MultipartFile addMainImage; //메인 이미지가 없다가 추가된 경우
//        String originMainImage; //기존 메인 이미지
        String rating;
        List<ModifyRequestArtList> artList;
}