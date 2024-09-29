package com.backend.Artview.domain.auth.domain.response;

import lombok.Data;

@Data
public class KakaoUserInfoResponseDto {

    private Long id;
    private KakaoAccount kakao_account;

    @Data
    public static class KakaoAccount {
        private Profile profile;
        private String email;
    }

    @Data
    public static class Profile {
        private String nickname;
        private String profile_image_url;
    }
}
