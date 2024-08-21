package com.backend.Artview.domain.myReviews.dto;

import com.backend.Artview.domain.myReviews.domain.MyReviewsContents;
import lombok.Builder;

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