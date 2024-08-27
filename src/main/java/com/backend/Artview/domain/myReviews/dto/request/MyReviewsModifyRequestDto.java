package com.backend.Artview.domain.myReviews.dto.request;

import java.util.List;

public record MyReviewsModifyRequestDto(
        Long id,
        Long myReviewsId,
        String name,
        String date,
        String gallery,
        String rating,
        List<RequestArtList> artList
) {
}