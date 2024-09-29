package com.backend.Artview.domain.auth.controller;

import com.backend.Artview.domain.auth.domain.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sign-up") //회원가입
    public KakaoSignUpResponseDto loginKakao(@RequestParam("code") String code) {
        log.info("인가코드 : " + code);
        return authService.signUpWithOauth2(code);
    }


    @PostMapping("/login") //로그인
    public void login() {

    }

//    @PostMapping("/reissue") //accessToken 재발행
//    public ReissueResponseDto reissue(@RequestBody ReissueRequestDto request) throws JsonProcessingException {
//        return authService.reissue(request);
//    }

}
