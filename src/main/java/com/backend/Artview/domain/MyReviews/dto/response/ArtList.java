package com.backend.Artview.domain.MyReviews.dto.response;

import com.backend.Artview.domain.MyReviews.domain.MyReviewsContents;
import lombok.Builder;


@Builder
record ArtList(
        String image,
        String title,
        String artist,
        String contents
) {
    public static ArtList of(MyReviewsContents MyReviewsContents){
        return ArtList.builder()
                .image(MyReviewsContents.getMyExhibitionImage().getMyExhibitionImages_Url())
                .title(MyReviewsContents.getArtTitle())
                .artist(MyReviewsContents.getArtist())
                .contents(MyReviewsContents.getNote())
                .build();
    }
}