package com.backend.Artview.domain.users.controller;

import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    public final Long userId = 10001L;

    private final UserService userService;

    @GetMapping("/myPage/userInfo")
    public MyPageUserInfoResponseDto getMyPageUserInfo() {
        return userService.getMyPageUserInfo(userId);
    }

    @GetMapping("/myPage/myReview")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview() {
        return userService.getMyPageMyReview(userId);
    }

    @GetMapping("/myPage/communication")
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication() {
        return userService.getMyPageCommunication(userId);
    }
}
