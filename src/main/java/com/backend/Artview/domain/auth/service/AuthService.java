package com.backend.Artview.domain.auth.service;

import com.backend.Artview.domain.auth.domain.request.KakaoSignUpRequestDto;
import com.backend.Artview.domain.auth.domain.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.domain.response.KakaoUserInfoResponseDto;

public interface AuthService {

    KakaoSignUpResponseDto signUpWithOauth2(String code);
}
