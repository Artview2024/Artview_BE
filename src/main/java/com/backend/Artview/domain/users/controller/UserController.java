package com.backend.Artview.domain.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String REDIRECT_URI = "http://localhost:8080/login/oauth2/kakao";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String CLIENT_ID = "639573a5e7d079727a4db674316d46b1";

    @GetMapping("/oauth2/kakao")
    public String loginKakao(@RequestParam("code") String code) {
        log.info("인가코드를 받습니다. : "+code);
        log.info("인가 코드를 이용해 토큰을 받습니다.");
        String uri = TOKEN_URI + "?grant_type=" + GRANT_TYPE + "&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&code=" + code;
        log.info("토큰 요청 URL : "+uri);

        return "인가코드 : "+code;
    }

}
