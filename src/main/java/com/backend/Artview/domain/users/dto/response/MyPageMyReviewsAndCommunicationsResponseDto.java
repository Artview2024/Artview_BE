package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;

@Builder
public record MyPageMyReviewsAndCommunicationsResponseDto(
        Long id,
        String imageUrl,
        String title,
        String date
) {

    public static MyPageMyReviewsAndCommunicationsResponseDto of(MyReviews myReviews){
        return MyPageMyReviewsAndCommunicationsResponseDto.builder()
                .id(myReviews.getId())
                .imageUrl(myReviews.getMainImageUrl())
                .title(myReviews.getExhibitionsTitle())
                .date(myReviews.getVisitedDate())
                .build();
    }

    public static MyPageMyReviewsAndCommunicationsResponseDto of(Communications communications){
        return MyPageMyReviewsAndCommunicationsResponseDto.builder()
                .id(communications.getId())
                .imageUrl(communications.getCommunicationImagesList().get(0).getImageUrl())
                .title(communications.getName())
                .date(communications.getDate())
                .build();
    }
}
