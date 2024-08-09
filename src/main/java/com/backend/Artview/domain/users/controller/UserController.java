package com.backend.Artview.domain.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @GetMapping("/oauth2/kakao")
    public void loginKakao() {
        ;
    }
}
