package com.backend.Artview.domain.myReviews.dto.response;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;


@Builder
public record  AllMyReviewsResponseDto(
        Long myReviewsId, //전시기록 id
        String exhibitionName,
        String imageUrl,//이미지 url
        String visitedDate,//방문 날짜
        Long exhibitionId
) {


    public static AllMyReviewsResponseDto of(MyReviews myReviews, Long crawlingExhibitionId) {
        return AllMyReviewsResponseDto.builder()
                .myReviewsId(myReviews.getId())
                .exhibitionName(myReviews.getExhibitionsTitle())
                .imageUrl(myReviews.getMainImageUrl())
                .visitedDate(myReviews.getVisitedDate())
                .exhibitionId(crawlingExhibitionId)
                .build();
    }
}
