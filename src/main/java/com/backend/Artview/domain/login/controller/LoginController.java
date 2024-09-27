package com.backend.Artview.domain.login.controller;

import com.backend.Artview.domain.login.service.LoginOauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginOauthService loginOauthService;

    @GetMapping("/oauth2/kakao")
    public void loginKakao(@RequestParam("code") String code) {
        loginOauthService.getKakaoAccessToken(code);
    }

}
