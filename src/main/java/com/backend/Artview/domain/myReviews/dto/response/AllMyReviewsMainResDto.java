package com.backend.Artview.domain.myReviews.dto.response;

import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;

@Builder
public record AllMyReviewsMainResDto(
        Long myReviewsId, //전시기록 id
        String exhibitionName,
        String visitedDate,//방문 날짜
        String location, //전시회 위치
        String imageUrl//이미지 url
) {
    public static AllMyReviewsMainResDto of(MyReviews myReviews){
        return AllMyReviewsMainResDto.builder()
                .myReviewsId(myReviews.getId())
                .exhibitionName(myReviews.getExhibitionsTitle())
                .visitedDate(myReviews.getVisitedDate())
                .location(checkMyReviewsCrawlingExhibition(myReviews))
                .imageUrl(myReviews.getMainImageUrl())
                .build();
    }

    private static String checkMyReviewsCrawlingExhibition(MyReviews myReviews){
        if (myReviews.getCrawlingExhibition() == null) return null;
        else return myReviews.getCrawlingExhibition().getLocation();
    }
}
