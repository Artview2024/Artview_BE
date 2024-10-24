package com.backend.Artview.domain.users.controller;

import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.dto.response.MyPageFollowAndMyReviewsNumberInfoResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyFollowListResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.service.UserService;
import com.backend.Artview.global.customAnnotation.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/myPage/userInfo")
    public MyPageUserInfoResponseDto getMyPageUserInfo(@UserId Long userId) {
        return userService.getMyPageUserInfo(userId);
    }

    @GetMapping("/myPage/totalNumber")
    public MyPageFollowAndMyReviewsNumberInfoResponseDto getMyPageTotalNumber(@UserId Long userId) {
        return userService.getMyPageTotalNumber(userId);
    }

    @GetMapping("/myPage/myReview")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(@UserId Long userId) {
        return userService.getMyPageMyReview(userId);
    }

    @GetMapping("/myPage/communication")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(@UserId Long userId) {
        return userService.getMyPageCommunication(userId);
    }

    @PutMapping("/follow")
    public void registerFollow(@UserId Long userId,@RequestBody FollowRequestDto dto){
        userService.registerFollow(userId, dto);
    }

    @DeleteMapping("/unfollow")
    public void deleteFollow(@UserId Long userId, @RequestBody FollowRequestDto dto){
        userService.deleteFollow(userId, dto);
    }

    @GetMapping("/myPage/myFollowingList") //내가 팔로우 하는 사람 리스트
    public MyPageMyFollowListResponseDto findMyPageMyFollowingList(@UserId Long userId){
        return userService.findMyPageMyFollowingList(userId);
    }
    @GetMapping("/myPage/myFollowerList") //나를 팔로우 하는 사람 리스트
    public MyPageMyFollowListResponseDto findMyPageMyFollowerList(@UserId Long userId){
        return userService.findMyPageMyFollowerList(userId);
    }
}
