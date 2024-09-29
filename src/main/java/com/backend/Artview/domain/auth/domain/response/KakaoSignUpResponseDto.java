package com.backend.Artview.domain.auth.domain.response;

import com.backend.Artview.domain.users.domain.Users;
import lombok.Builder;

@Builder
public record KakaoSignUpResponseDto(
        Long userId,
        String name,
        String accessToken,
        String refreshToken
) {
    public static KakaoSignUpResponseDto of(Users user, String  accessToken, String refreshToken) {
        return KakaoSignUpResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
