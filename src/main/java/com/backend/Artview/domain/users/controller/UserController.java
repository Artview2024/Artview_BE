package com.backend.Artview.domain.users.controller;

import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.service.UserService;
import com.backend.Artview.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

//    public final Long userId = 10001L;

    private final UserService userService;

    private final JwtProvider jwtProvider;
    @GetMapping("/myPage/userInfo")
    public MyPageUserInfoResponseDto getMyPageUserInfo(@RequestHeader("Authorization") String authorizationHeader) {

        String accessToken = jwtProvider.getTokenFromHeader(authorizationHeader);

        Long userId = jwtProvider.getUserId(accessToken);
        return userService.getMyPageUserInfo(userId);
    }

    @GetMapping("/myPage/myReview")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview() {
        return userService.getMyPageMyReview(10001L);
    }

    @GetMapping("/myPage/communication")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication() {
        return userService.getMyPageCommunication(10001L);
    }

    @PutMapping("/follow")
    public void registerFollow(@RequestHeader("Authorization") String authorizationHeader,@RequestBody FollowRequestDto dto){
        userService.registerFollow(authorizationHeader, dto);
    }

    @DeleteMapping("/unfollow")
    public void deleteFollow(@RequestHeader("Authorization") String authorizationHeader,@RequestBody FollowRequestDto dto){
        userService.deleteFollow(authorizationHeader, dto);
    }
}
