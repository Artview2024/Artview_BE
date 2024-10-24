package com.backend.Artview.domain.users.dto.response;

import com.backend.Artview.domain.users.domain.Follow;
import com.backend.Artview.domain.users.domain.Users;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record MyPageMyFollowListResponseDto(
        List<FollowInfo> followInfoList
) {


    @Builder
    public record FollowInfo(
            Long userId,
            String userName,
            String userImageUrl
    ) {
        public static FollowInfo of(Users user) {
            return FollowInfo.builder()
                    .userId(user.getId())
                    .userName(user.getName())
                    .userImageUrl(user.getUserImage())
                    .build();
        }
    }

    public static MyPageMyFollowListResponseDto of(List<Users> followingList) {

        return MyPageMyFollowListResponseDto.builder()
                .followInfoList(followingList.stream().map(FollowInfo::of).collect(Collectors.toList()))
                .build();
    }
}
