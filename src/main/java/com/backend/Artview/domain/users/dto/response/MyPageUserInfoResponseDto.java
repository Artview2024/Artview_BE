package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.users.domain.Users;
import lombok.Builder;

@Builder
public record MyPageUserInfoResponseDto(
        Long userId,
        String userName,
        String userImageUrl

) {
    public static MyPageUserInfoResponseDto of(Users user) {
        return MyPageUserInfoResponseDto.builder()
                .userId(user.getId())
                .userName(user.getName())
                .userImageUrl(user.getUserImage())
                .build();
    }
}
