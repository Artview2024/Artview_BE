package com.backend.Artview.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record ReissueResponseDto(

        String accessToken,
        String refreshToken
) {
    public static ReissueResponseDto of(String accessToken, String refreshToken) {
        return ReissueResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
