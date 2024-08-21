package com.backend.Artview.domain.communication.dto.response;

import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;

import java.util.List;

@Builder
public record CommunicationRetrieveResponseDto(
        Long id,
        String name,
        String rate,
        String date,
        String gallery,
        List<String> images
) {

    public static CommunicationRetrieveResponseDto of(MyReviews myReviews,List<String> images) {
        return CommunicationRetrieveResponseDto.builder()
                .id(myReviews.getId())
                .name(myReviews.getExhibitionsTitle())
                .rate(myReviews.getGrade())
                .date(myReviews.getVisitedDate())
                .gallery(myReviews.getExhibitionsLocation())
                .images(images)
                .build();
    }
}
