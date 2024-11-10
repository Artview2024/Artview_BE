package com.backend.Artview.domain.users.controller;

import com.backend.Artview.domain.users.dto.request.ModifyMyPageInfoRequestDto;
import com.backend.Artview.domain.users.dto.request.SaveUsersInterestRequestDto;
import com.backend.Artview.domain.users.dto.response.*;
import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.service.UserService;
import com.backend.Artview.global.customAnnotation.UserId;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/myPage/myFollowingList") //내가 팔로잉 하는 사람 리스트
    public List<MyPageFollowInfoDto> findMyPageMyFollowingList(@UserId Long userId){
        return userService.findMyPageMyFollowingList(userId);
    }
    @GetMapping("/myPage/myFollowerList") //나를 팔로우 하는 사람 리스트
    public List<MyPageFollowInfoDto> findMyPageMyFollowerList(@UserId Long userId){
        return userService.findMyPageMyFollowerList(userId);
    }

    @GetMapping("/myPage/interest")
    public List<String> findUsersInterest(@UserId Long userId){
        return userService.findUsersInterest(userId);
    }

    @PatchMapping("/modify/myPage")
    public void modifyMyPageInfo(@UserId Long userId, @ModelAttribute ModifyMyPageInfoRequestDto dto){
        userService.modifyMyPageInfo(userId, dto);
    }

    @PostMapping("/save/interest")
    public void saveUsersInterest(@UserId Long userId, @RequestBody SaveUsersInterestRequestDto dto){
        userService.saveUsersInterest(userId,dto);
    }

    @GetMapping("/recommend/follower")
    public List<MyPageUserInfoResponseDto> recommendFollowerBasedOnInterests(@UserId Long userId){
        return userService.recommendFollowerBasedOnInterests(userId);
    }

//    다른 사용자 프로필 조회 api
    @GetMapping("/userInfo/{writerId}")
    public MyPageUserInfoResponseDto getWriterUserInfo(@PathVariable Long writerId) {
        return userService.getMyPageUserInfo(writerId);
    }

    @GetMapping("/totalNumber/{writerId}")
    public MyPageFollowAndMyReviewsNumberInfoResponseDto getWriterTotalNumber(@PathVariable Long writerId) {
        return userService.getMyPageTotalNumber(writerId);
    }

    @GetMapping("/myReview/{writerId}")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getWriterMyReview(@PathVariable Long writerId) {
        return userService.getMyPageMyReview(writerId);
    }

    @GetMapping("/communication/{writerId}")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getWriterCommunication(@PathVariable Long writerId) {
        return userService.getMyPageCommunication(writerId);
    }

    @GetMapping("/checkFollow/{writerId}")
    public boolean checkUsersFollow(@UserId Long userId, @PathVariable Long writerId) {
        return userService.checkUsersFollow(userId, writerId);
    }

    @GetMapping("/FollowingList/{writerId}")
    public List<MyPageFollowInfoDto> findWriterMyFollowingList(@PathVariable Long writerId){
        return userService.findMyPageMyFollowingList(writerId);
    }
    @GetMapping("/FollowerList/{writerId}")
    public List<MyPageFollowInfoDto> findWriterMyFollowerList(@PathVariable Long writerId){
        return userService.findMyPageMyFollowerList(writerId);
    }

    @GetMapping("interest/{writerId}")
    public List<String> findWriterInterest(@PathVariable Long writerId){
        return userService.findUsersInterest(writerId);
    }
//    다른 사용자 프로필 조회 api

}
