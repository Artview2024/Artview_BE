package com.backend.Artview.domain.auth.domain.request;

public record KakaoSignUpRequestDto(
        String kakaoAccessToken,
        String kakaoId,
        String email,
        String name,
        String userImage
) {
}
