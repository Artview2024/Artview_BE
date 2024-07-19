package com.backend.Artview.domain.MyReviews.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


public record ArtList(
        String image,
        String title,
        String artist,
        String contents
) {
}
