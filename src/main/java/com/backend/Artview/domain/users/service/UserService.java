package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;

import java.util.List;

public interface UserService {

    MyPageUserInfoResponseDto getMyPageUserInfo(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId);

    void registerFollow(Long userId, FollowRequestDto dto);

    void deleteFollow(Long userId, FollowRequestDto dto);
}
