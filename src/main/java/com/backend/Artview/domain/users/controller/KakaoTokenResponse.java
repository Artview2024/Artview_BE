package com.backend.Artview.domain.users.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KakaoTokenResponse {
    String code;
    String error;
    String error_description;
    String state;
}
