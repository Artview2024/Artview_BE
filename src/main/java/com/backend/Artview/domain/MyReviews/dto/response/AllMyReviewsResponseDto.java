package com.backend.Artview.domain.MyReviews.dto.response;

import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import lombok.Builder;


@Builder
public record  AllMyReviewsResponseDto(

        Long myReviewsId, //전시기록 id
        String imageUrl,//이미지 url
        String visitedDate//방문 날짜
) {


    public static AllMyReviewsResponseDto of(MyReviews myReviews) {
        return AllMyReviewsResponseDto.builder()
                .myReviewsId(myReviews.getId())
                .imageUrl(myReviews.getMainImageUrl())
                .visitedDate(myReviews.getVisitedDate())
                .build();
    }
}
