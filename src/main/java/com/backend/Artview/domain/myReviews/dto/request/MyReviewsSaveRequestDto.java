package com.backend.Artview.domain.myReviews.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public record MyReviewsSaveRequestDto(
        Long id,
        String name,
        String date,
        String gallery,
        String rating,
        List<RequestArtList> artList

) {
}
