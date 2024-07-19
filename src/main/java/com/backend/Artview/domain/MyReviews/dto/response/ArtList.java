package com.backend.Artview.domain.MyReviews.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public record ArtList(
        String image,
        String title,
        String artist,
        String contents
) {
}
