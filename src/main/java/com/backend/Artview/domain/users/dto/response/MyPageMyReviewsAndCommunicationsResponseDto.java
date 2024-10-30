package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.communication.domain.CommunicationImages;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;

import java.util.List;

@Builder
public record MyPageMyReviewsAndCommunicationsResponseDto(
        Long id,
        String imageUrl,
        String title,
        String date,
        String gallery
) {

    public static MyPageMyReviewsAndCommunicationsResponseDto of(MyReviews myReviews){
        return MyPageMyReviewsAndCommunicationsResponseDto.builder()
                .id(myReviews.getId())
                .imageUrl(myReviews.getMainImageUrl())
                .title(myReviews.getExhibitionsTitle())
                .date(myReviews.getVisitedDate())
                .gallery(myReviews.getExhibitionsLocation())
                .build();
    }

    public static MyPageMyReviewsAndCommunicationsResponseDto of(Communications communications){
        return MyPageMyReviewsAndCommunicationsResponseDto.builder()
                .id(communications.getId())
                .imageUrl(checkImageUrlIsNull(communications.getCommunicationImagesList()))
                .title(communications.getName())
                .date(communications.getDate())
                .gallery(communications.getGallery())
                .build();
    }

    private static String checkImageUrlIsNull(List<CommunicationImages> communicationImagesList){
        if(!communicationImagesList.isEmpty() && communicationImagesList.get(0) != null)
            return communicationImagesList.get(0).getImageUrl();
        else return null;
    }
}
