package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.dto.response.MyPageFollowAndMyReviewsNumberInfoResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyFollowListResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;

import java.util.List;

public interface UserService {

    MyPageUserInfoResponseDto getMyPageUserInfo(Long userId);

    MyPageFollowAndMyReviewsNumberInfoResponseDto getMyPageTotalNumber(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId);

    void registerFollow(Long userId, FollowRequestDto dto);

    void deleteFollow(Long userId, FollowRequestDto dto);

    MyPageMyFollowListResponseDto findMyPageMyFollowingList(Long userId);

    MyPageMyFollowListResponseDto findMyPageMyFollowerList(Long userId);
}
