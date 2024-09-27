package com.backend.Artview.domain.login.service;

import com.backend.Artview.domain.login.domain.KakaoTokenResponseDto;
import com.backend.Artview.domain.login.domain.KakaoUserInfoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class LoginOauthService {

    @Value("${oauth2.client.registration.kakao.token-uri}")
    private static String TOKEN_URI;

    @Value("${oauth2.client.registration.kakao.user-info-uri}")
    private static String USER_INFO_URI;

    @Value("${oauth2.client.registration.kakao.redirect-uri}")
    private static String REDIRECT_URI;

    @Value("${oauth2.client.registration.kakao.grant-type}")
    private static String GRANT_TYPE;

    @Value("${oauth2.client.registration.kakao.client-id}")
    private static String CLIENT_ID;

    @Value("${oauth2.client.registration.kakao.client-secret}")
    private static String CLIENT_SECRET;

    @Value("${oauth2.client.registration.kakao.content-type}")
    private static String CONTENT_TYPE;
    RestTemplate rt = new RestTemplate();

    public void getKakaoAccessToken(String code){

        // HTTP POST를 요청할 때 보내는 데이터(body)를 설명해주는 헤더도 만들어 같이 보내줘야 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", CONTENT_TYPE);

        // body 데이터를 담을 오브젝트인 MultiValueMap를 만들어보자
        // body는 보통 key, value의 쌍으로 이루어지기 때문에 자바에서 제공해주는 MultiValueMap 타입을 사용한다.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", GRANT_TYPE);
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);
        params.add("client_secret", CLIENT_SECRET);

        // 요청하기 위해 헤더(Header)와 데이터(Body)를 합친다.
        // kakaoTokenRequest는 데이터(Body)와 헤더(Header)를 Entity가 된다.
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // POST 방식으로 Http 요청한다. 그리고 response 변수의 응답 받는다.
        ResponseEntity<KakaoTokenResponseDto> kakaoTokenResponse = rt.exchange(
                TOKEN_URI, // https://{요청할 서버 주소}
                HttpMethod.POST, // 요청할 방식
                kakaoTokenRequest, // 요청할 때 보낼 데이터
                KakaoTokenResponseDto.class // 요청 시 반환되는 데이터 타입
        );

        log.info("accessTokenStatusCode : "+kakaoTokenResponse.getStatusCode());
        log.info("accessToken : "+kakaoTokenResponse.getBody().getAccess_token());
        log.info("refreshToken : "+kakaoTokenResponse.getBody().getRefresh_token());

        getUserInfo(String.valueOf(kakaoTokenResponse.getBody().getAccess_token()));
    }

    public void getUserInfo(String accessToken){
        // HTTP POST를 요청할 때 보내는 데이터(body)를 설명해주는 헤더도 만들어 같이 보내줘야 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + accessToken);
        headers.add("Content-Type", CONTENT_TYPE);

        HttpEntity<String> kakaoUserInfoRequest = new HttpEntity<>(headers);


        // POST 방식으로 Http 요청한다. 그리고 response 변수의 응답 받는다.
        ResponseEntity<KakaoUserInfoResponseDto> kakaoUserInfoResponse = rt.exchange(
                USER_INFO_URI, // https://{요청할 서버 주소}
                HttpMethod.GET, // 요청할 방식
                kakaoUserInfoRequest, // 요청할 때 보낼 데이터
                KakaoUserInfoResponseDto.class // 요청 시 반환되는 데이터 타입
        );

        log.info("userInfoResponseCode : "+kakaoUserInfoResponse.getStatusCode());
        log.info("userInfoResponse : "+kakaoUserInfoResponse.getBody());
    }
}
