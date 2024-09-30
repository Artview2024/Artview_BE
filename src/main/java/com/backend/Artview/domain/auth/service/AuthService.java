package com.backend.Artview.domain.auth.service;

import com.backend.Artview.domain.auth.dto.request.ReissueRequestDto;
import com.backend.Artview.domain.auth.dto.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.dto.response.ReissueResponseDto;

public interface AuthService {

    KakaoSignUpResponseDto signUpWithOauth2(String accessToken);

    ReissueResponseDto reissue(ReissueRequestDto dto);
}
