package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.users.domain.Users;
import lombok.Builder;

@Builder
public record MyPageFollowInfoDto(
        Long userId,
        String userName,
        String userImageUrl,
        boolean isFollowing
) {
    public static MyPageFollowInfoDto of(Users user, boolean isFollowing) {
        return MyPageFollowInfoDto.builder()
                .userId(user.getId())
                .userName(user.getName())
                .userImageUrl(user.getUserImage())
                .isFollowing(isFollowing)
                .build();
    }
}
