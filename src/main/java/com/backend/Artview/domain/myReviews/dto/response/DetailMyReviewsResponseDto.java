package com.backend.Artview.domain.myReviews.dto.response;

import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.domain.MyReviewsContents;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DetailMyReviewsResponseDto(
        Long id, //ReviewId
        String name,//전시회 명
        String date,//방문 나라짜
        String gallery,//갤러리 이름
        String mainImage,
        String rating,//별점
        List<ResponseArtList> artList//내용
) {

    public static DetailMyReviewsResponseDto of(MyReviews myReview, List<MyReviewsContents> myReviewsContents) {
        return DetailMyReviewsResponseDto.builder()
                .id(myReview.getId())
                .name(myReview.getExhibitionsTitle())
                .date(myReview.getVisitedDate())
                .gallery(myReview.getExhibitionsLocation())
                .mainImage(myReview.getMainImageUrl())
                .rating(myReview.getGrade())
                .artList(myReviewsContents.stream().map(v->ResponseArtList.of(v)).collect(Collectors.toList())) //
                .build();
    }
}
