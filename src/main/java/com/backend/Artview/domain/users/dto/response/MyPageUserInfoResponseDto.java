package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.users.domain.Users;
import lombok.Builder;

@Builder
public record MyPageUserInfoResponseDto(
        Long userId,
        String userName,
        String userImageUrl,
        int followees,
        int follower,
        int numberOfMyReviews //관람수

) {
    public static MyPageUserInfoResponseDto of(Users user,int followees,int followers,int numberOfMyReviews) {
        return MyPageUserInfoResponseDto.builder()
                .userId(user.getId())
                .userName(user.getName())
                .userImageUrl(user.getUserImage())
                .followees(followees)
                .follower(followers)
                .numberOfMyReviews(numberOfMyReviews)
                .build();
    }
}
