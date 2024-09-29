package com.backend.Artview.domain.auth.service;

import com.backend.Artview.domain.auth.domain.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.domain.response.KakaoTokenResponseDto;
import com.backend.Artview.domain.auth.domain.response.KakaoUserInfoResponseDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
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

import static com.backend.Artview.domain.users.exception.UserErrorCode.DUPLICATE_KAKAO_ID;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Value("${oauth2.client.registration.kakao.token-uri}")
    private String TOKEN_URI;

    @Value("${oauth2.client.registration.kakao.user-info-uri}")
    private String USER_INFO_URI;

    @Value("${oauth2.client.registration.kakao.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${oauth2.client.registration.kakao.grant-type}")
    private String GRANT_TYPE;

    @Value("${oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${oauth2.client.registration.kakao.client-secret}")
    private String CLIENT_SECRET;

    @Value("${oauth2.client.registration.kakao.content-type}")
    private String CONTENT_TYPE;

    private final UsersRepository userRepository;
    private final JwtProvider jwtProvider;
    RestTemplate rt = new RestTemplate();


    @Override
    public KakaoSignUpResponseDto signUpWithOauth2(String code) {
        String kakaoAccessToken = getKakaoAccessToken(code);
        KakaoUserInfoResponseDto kakaoUserInfo = getUserInfoUsingAccessToken(kakaoAccessToken);

        // 이미 회원가입 된 유저인지 확인
        validateNotAlreadySignIn(kakaoUserInfo.getId());

        Users user = userRepository.save(Users.toEntity(kakaoUserInfo));

        return KakaoSignUpResponseDto.of(user,jwtProvider.createAccessToken(user.getId()),jwtProvider.createRefreshToken());
    }

    private void validateNotAlreadySignIn(Long id) {
        if(userRepository.existsById(id)) throw new UserException(DUPLICATE_KAKAO_ID);
    }

    public String getKakaoAccessToken(String code){
        log.info("URI : "+TOKEN_URI);
        log.info("CONTENT_TYPE : "+CONTENT_TYPE);

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
                TOKEN_URI,
                HttpMethod.POST, // 요청할 방식
                kakaoTokenRequest, // 요청할 때 보낼 데이터
                KakaoTokenResponseDto.class // 요청 시 반환되는 데이터 타입
        );

        log.info("accessToken : "+kakaoTokenResponse.getBody().getAccess_token());
        log.info("refreshToken : "+kakaoTokenResponse.getBody().getRefresh_token());

        return kakaoTokenResponse.getBody().getAccess_token();
    }

    public KakaoUserInfoResponseDto getUserInfoUsingAccessToken(String accessToken){
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

        return kakaoUserInfoResponse.getBody();
    }

}