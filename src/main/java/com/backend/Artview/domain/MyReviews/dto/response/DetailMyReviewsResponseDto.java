package com.backend.Artview.domain.MyReviews.dto.response;

import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import com.backend.Artview.domain.MyReviews.domain.MyReviewsContents;
import com.backend.Artview.domain.MyReviews.dto.ArtList;
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
        List<ArtList> artList//내용
) {

    public static DetailMyReviewsResponseDto of(MyReviews myReview,List<MyReviewsContents> myReviewsContents) {
        return DetailMyReviewsResponseDto.builder()
                .id(myReview.getId())
                .name(myReview.getExhibitionsTitle())
                .date(myReview.getVisitedDate())
                .gallery(myReview.getExhibitionsLocation())
                .mainImage(myReview.getMainImageUrl())
                .rating(Float.toString(myReview.getGrade()))
                .artList(myReviewsContents.stream().map(v->ArtList.of(v)).collect(Collectors.toList()))
                .build();
    }
}
