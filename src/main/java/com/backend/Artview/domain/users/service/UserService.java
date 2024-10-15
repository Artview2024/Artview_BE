package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;

import java.util.List;

public interface UserService {

    MyPageUserInfoResponseDto getMyPageUserInfo(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId);

    void registerFollow(String authorizationHeader, FollowRequestDto dto);

    void deleteFollow(String authorizationHeader, FollowRequestDto dto);
}
