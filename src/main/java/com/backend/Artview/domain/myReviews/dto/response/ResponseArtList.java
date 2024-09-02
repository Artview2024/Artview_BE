package com.backend.Artview.domain.myReviews.dto.response;

import com.backend.Artview.domain.myReviews.domain.MyReviewsContents;
import lombok.Builder;

@Builder
public record ResponseArtList(
        String image,
        String title,
        String artist,
        String contents
) {
    public static ResponseArtList of(MyReviewsContents MyReviewsContents){
        return ResponseArtList.builder()
                .image(MyReviewsContents.getMyExhibitionImage().getMyExhibitionImagesUrl())
                .title(MyReviewsContents.getArtTitle())
                .artist(MyReviewsContents.getArtist())
                .contents(MyReviewsContents.getNote())
                .build();
    }
}