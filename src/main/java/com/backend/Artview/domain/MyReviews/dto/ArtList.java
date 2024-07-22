package com.backend.Artview.domain.MyReviews.dto;

import com.backend.Artview.domain.MyReviews.domain.MyReviewsContents;
import lombok.Builder;
import lombok.Getter;


@Builder
public record ArtList(
        String image,
        String title,
        String artist,
        String contents
) {
    public static ArtList of(MyReviewsContents MyReviewsContents){
        return ArtList.builder()
                .image(MyReviewsContents.getMyExhibitionImage().getMyExhibitionImagesUrl())
                .title(MyReviewsContents.getArtTitle())
                .artist(MyReviewsContents.getArtist())
                .contents(MyReviewsContents.getNote())
                .build();
    }
}