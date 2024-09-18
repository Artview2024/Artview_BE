package com.backend.Artview.domain.communication.dto.response;

import com.backend.Artview.domain.myReviews.domain.MyReviews;
import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
public record CommunicationRetrieveResponseDto(
        Long id,
        String name,
        String rate,
        String date,
        String gallery,
//        List<String> images
        Map<String,String> imageAndTitle
) {

    public static CommunicationRetrieveResponseDto of(MyReviews myReviews,Map<String,String> imageAndTitle) {
        return CommunicationRetrieveResponseDto.builder()
                .id(myReviews.getId())
                .name(myReviews.getExhibitionsTitle())
                .rate(myReviews.getGrade())
                .date(myReviews.getVisitedDate())
                .gallery(myReviews.getExhibitionsLocation())
//                .images(images)
                .imageAndTitle(imageAndTitle)
                .build();
    }
}
