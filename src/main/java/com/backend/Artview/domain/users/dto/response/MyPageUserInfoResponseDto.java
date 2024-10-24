package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.users.domain.Users;
import lombok.Builder;

@Builder
public record MyPageUserInfoResponseDto(
        Long userId,
        String userName,
        String userImageUrl,
        int following,
        int follower,
        int numberOfMyReviews //관람수

) {
    public static MyPageUserInfoResponseDto of(Users user,int following,int followers,int numberOfMyReviews) {
        return MyPageUserInfoResponseDto.builder()
                .userId(user.getId())
                .userName(user.getName())
                .userImageUrl(user.getUserImage())
                .following(following)
                .follower(followers)
                .numberOfMyReviews(numberOfMyReviews)
                .build();
    }
}
