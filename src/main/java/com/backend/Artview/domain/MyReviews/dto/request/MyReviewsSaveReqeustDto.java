package com.backend.Artview.domain.MyReviews.dto.request;

import com.backend.Artview.domain.MyReviews.dto.ArtList;

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
