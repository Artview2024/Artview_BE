package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.dto.request.ModifyMyPageInfoRequestDto;
import com.backend.Artview.domain.users.dto.request.SaveUsersInterestRequestDto;
import com.backend.Artview.domain.users.dto.response.*;

import java.util.List;

public interface UserService {

    MyPageUserInfoResponseDto getMyPageUserInfo(Long userId);

    MyPageFollowAndMyReviewsNumberInfoResponseDto getMyPageTotalNumber(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId);

    List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId);

    void registerFollow(Long userId, FollowRequestDto dto);

    void deleteFollow(Long userId, FollowRequestDto dto);

    List<MyPageFollowInfoDto> findMyPageMyFollowingList(Long userId);

    List<MyPageFollowInfoDto> findMyPageMyFollowerList(Long userId);

    boolean checkUsersFollow(Long userId, Long writerId);

    void modifyMyPageInfo(Long userId, ModifyMyPageInfoRequestDto dto);

    void saveUsersInterest(Long userId, SaveUsersInterestRequestDto dto);
}
