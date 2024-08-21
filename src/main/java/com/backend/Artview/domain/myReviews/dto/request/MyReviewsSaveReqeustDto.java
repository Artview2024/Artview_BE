package com.backend.Artview.domain.myReviews.dto.request;

import com.backend.Artview.domain.myReviews.dto.ArtList;

import java.util.List;


public record MyReviewsSaveReqeustDto(
        Long id,
        String name,
        String date,
        String gallery,
        String mainImage,
        String rating,
        List<ArtList> artList

) {
}
