package com.backend.Artview.domain.myReviews.dto.response;

import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;


@Builder
public record  AllMyReviewsResponseDto(
        Long myReviewsId, //전시기록 id
        String exhibitionName,
        String imageUrl,//이미지 url
        String visitedDate//방문 날짜
) {


    public static AllMyReviewsResponseDto of(MyReviews myReviews) {
        return AllMyReviewsResponseDto.builder()
                .myReviewsId(myReviews.getId())
                .exhibitionName(myReviews.getExhibitionsTitle())
                .imageUrl(myReviews.getMainImageUrl())
                .visitedDate(myReviews.getVisitedDate())
                .build();
    }
}
