package com.backend.Artview.domain.auth.service;

import com.backend.Artview.domain.auth.dto.request.LogOutRequestDto;
import com.backend.Artview.domain.auth.dto.request.ReissueRequestDto;
import com.backend.Artview.domain.auth.dto.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.dto.response.KakaoUserInfoResponseDto;
import com.backend.Artview.domain.auth.dto.response.ReissueResponseDto;

public interface AuthService {

    String getKakaoAccessToken(String code);

    KakaoUserInfoResponseDto getUserInfoUsingAccessToken(String kakaoAccessToken);

    KakaoSignUpResponseDto signUpWithOauth2(KakaoUserInfoResponseDto kakaoUserInfo);

    ReissueResponseDto reissue(ReissueRequestDto dto);

    void logOut(LogOutRequestDto dto);
}
