package com.backend.Artview.domain.exhibition.dto.response;

import com.backend.Artview.domain.communication.domain.Communications;
import lombok.Builder;

@Builder
public record ExhibitionDetailReviewResponseDto(
        Long userId,
        String userName,
        String userImageUrl,
        String rate,
        String content
) {
    public static ExhibitionDetailReviewResponseDto of(Communications communications) {
        return ExhibitionDetailReviewResponseDto.builder()
                .userId(communications.getUsers().getId())
                .userName(communications.getUsers().getName())
                .userImageUrl(communications.getUsers().getUserImage())
                .rate(communications.getRate())
                .content(communications.getContent())
                .build();
    }
}
