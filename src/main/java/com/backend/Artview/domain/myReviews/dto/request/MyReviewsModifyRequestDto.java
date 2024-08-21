package com.backend.Artview.domain.myReviews.dto.request;

import com.backend.Artview.domain.myReviews.dto.ArtList;

import java.util.List;

public record MyReviewsModifyRequestDto(
        Long id,
        Long myReviewsId,
        String name,
        String date,
        String gallery,
        String mainImage,
        String rating,
        List<ArtList> artList
) {
}