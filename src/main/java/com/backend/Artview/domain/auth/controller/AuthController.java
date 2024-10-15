package com.backend.Artview.domain.auth.controller;

import com.backend.Artview.domain.auth.dto.request.LogOutRequestDto;
import com.backend.Artview.domain.auth.dto.request.ReissueRequestDto;
import com.backend.Artview.domain.auth.dto.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.dto.response.KakaoUserInfoResponseDto;
import com.backend.Artview.domain.auth.dto.response.ReissueResponseDto;
import com.backend.Artview.domain.auth.service.AuthService;
import com.backend.Artview.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @GetMapping("/kakao-login/callback") //회원가입
    public void kakaoLoginCallBack(@RequestParam(name="code") String kakaoCode) {
        log.info("callback 인가코드 : " + kakaoCode);
    }

    @GetMapping("/kakao-login") //회원가입
    public KakaoSignUpResponseDto loginKakao(@RequestParam(name="code") String code) {
        String kakaoAccessToken = authService.getKakaoAccessToken(code);
        KakaoUserInfoResponseDto userInfoUsingAccessToken = authService.getUserInfoUsingAccessToken(kakaoAccessToken);
        return authService.signUpWithOauth2(userInfoUsingAccessToken);
    }

    @PostMapping("/reissue") //accessToken 재발행
    public ReissueResponseDto reissue(@RequestBody ReissueRequestDto dto) {
        log.info("reissue");
        return authService.reissue(dto);
    }

    @DeleteMapping("/log-out")
    public void logOut(@RequestBody LogOutRequestDto dto) {
        authService.logOut(dto);
    }

    @GetMapping("/jwt-access-token")
    public String jwt(@RequestHeader(name = "userId") Long userId){
        return jwtProvider.createAccessToken(userId);
    }
}
