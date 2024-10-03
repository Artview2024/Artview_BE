package com.backend.Artview.domain.auth.dto.request;

public record LogOutRequestDto(
        String accessToken,
        String refreshToken
) {
}
