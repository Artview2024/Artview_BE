package com.backend.Artview.domain.auth.domain.response;

import lombok.Data;

@Data
public class KakaoTokenResponseDto {
    private String token_type;
    private String access_token;
    private int expires_in; //액세스 토큰 만료 시간(초)
    private String refresh_token; //리프레시 토큰 만료 시간(초)
    private int refresh_token_expires_in;
    private String scope; //인증된 사용자의 정보 조회 권한 범위
}
