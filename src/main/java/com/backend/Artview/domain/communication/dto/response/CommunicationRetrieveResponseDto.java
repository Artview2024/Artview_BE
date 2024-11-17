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
        Map<String,String> imageAndTitle,
        Long exhibitionId
) {

    public static CommunicationRetrieveResponseDto of(MyReviews myReviews,Map<String,String> imageAndTitle) {
        return CommunicationRetrieveResponseDto.builder()
                .id(myReviews.getId())
                .name(myReviews.getExhibitionsTitle())
                .rate(myReviews.getGrade())
                .date(myReviews.getVisitedDate())
                .gallery(myReviews.getExhibitionsLocation())
                .imageAndTitle(imageAndTitle)
                .exhibitionId(checkExhibitionId(myReviews))
                .build();
    }

    private static Long checkExhibitionId(MyReviews myReviews){
        if (myReviews.getCrawlingExhibition()==null)
            return null;
        else
            return myReviews.getCrawlingExhibition().getId();
    }
}
