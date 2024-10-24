package com.backend.Artview.domain.users.dto.response;

import lombok.Builder;

@Builder
public record MyPageFollowAndMyReviewsNumberInfoResponseDto(
        int following,
        int follower,
        int numberOfMyReviews //관람수
) {

    public static MyPageFollowAndMyReviewsNumberInfoResponseDto of(int following, int follower, int numberOfReviews) {
        return MyPageFollowAndMyReviewsNumberInfoResponseDto.builder()
                .following(following)
                .follower(follower)
                .numberOfMyReviews(numberOfReviews)
                .build();
    }
}
