package com.backend.Artview.domain.myReviews.dto.request;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import lombok.Builder;

@Builder
public record MyReviewExhibitionInfoResDto(
        Long exhibitionId,
        String exhibition
) {
    public static MyReviewExhibitionInfoResDto ofTitle(CrawlingExhibition crawlingExhibition){
        return MyReviewExhibitionInfoResDto.builder()
                .exhibitionId(crawlingExhibition.getId())
                .exhibition(crawlingExhibition.getTitle())
                .build();
    }

    public static MyReviewExhibitionInfoResDto ofLocation(CrawlingExhibition crawlingExhibition){
        return MyReviewExhibitionInfoResDto.builder()
                .exhibitionId(crawlingExhibition.getId())
                .exhibition(crawlingExhibition.getLocation())
                .build();
    }
}
