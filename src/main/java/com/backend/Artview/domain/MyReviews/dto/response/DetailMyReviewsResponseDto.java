package com.backend.Artview.domain.MyReviews.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public record DetailMyReviewsResponseDto(
        Long id,
        String name,
        String date,
        String gallery,
        String MainImage,
        String rating,
        List<ArtList> artList
) {



}
