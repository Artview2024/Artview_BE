package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record RequestArtList(
//        String image,
        String title,
        String artist,
        String contents
) {}
