package com.backend.Artview.domain.auth.service;

import com.backend.Artview.domain.auth.domain.RefreshToken;
import com.backend.Artview.domain.auth.dto.request.LogOutRequestDto;
import com.backend.Artview.domain.auth.dto.request.ReissueRequestDto;
import com.backend.Artview.domain.auth.dto.response.KakaoSignUpResponseDto;
import com.backend.Artview.domain.auth.dto.response.KakaoTokenResponseDto;
import com.backend.Artview.domain.auth.dto.response.KakaoUserInfoResponseDto;
import com.backend.Artview.domain.auth.dto.response.ReissueResponseDto;
import com.backend.Artview.domain.auth.repository.RefreshTokenRepository;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.global.jwt.JwtProvider;
import com.backend.Artview.global.jwt.jwtException.JwtException;
import jakarta.transaction.Transactional;
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

import java.util.Optional;

import static com.backend.Artview.domain.users.exception.UserErrorCode.DUPLICATE_KAKAO_ID;
import static com.backend.Artview.global.jwt.jwtException.JwtErrorCode.REFRESH_TOKEN_NOT_MATCH;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

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
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    RestTemplate rt = new RestTemplate();

    @Override
    @Transactional
    public String getKakaoAccessToken(String code) {

        // HTTP POST를 요청할 때 보내는 데이터(body)를 설명해주는 헤더도 만들어 같이 보내줘야 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", CONTENT_TYPE);

        // body 데이터를 담을 오브젝트인 MultiValueMap
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

        return kakaoTokenResponse.getBody().getAccess_token();
    }

    @Override
    @Transactional
    public KakaoUserInfoResponseDto getUserInfoUsingAccessToken(String kakaoAccessToken) {
        // HTTP POST를 요청할 때 보내는 데이터(body)를 설명해주는 헤더도 만들어 같이 보내줘야 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-Type", CONTENT_TYPE);

        HttpEntity<String> kakaoUserInfoRequest = new HttpEntity<>(headers);


        // POST 방식으로 Http 요청한다. 그리고 response 변수의 응답 받는다.
        ResponseEntity<KakaoUserInfoResponseDto> kakaoUserInfoResponse = rt.exchange(
                USER_INFO_URI, // https://{요청할 서버 주소}
                HttpMethod.GET, // 요청할 방식
                kakaoUserInfoRequest, // 요청할 때 보낼 데이터
                KakaoUserInfoResponseDto.class // 요청 시 반환되는 데이터 타입
        );

        return kakaoUserInfoResponse.getBody();
    }


    @Override
    @Transactional
    public KakaoSignUpResponseDto signUpWithOauth2(KakaoUserInfoResponseDto kakaoUserInfo) {
        Users user;


        // 이미 회원가입 된 유저인지 확인
        Optional<Users> optionalUsers = validateUserAlreadySignIn(kakaoUserInfo.getId());

        if (optionalUsers.isPresent()) {
            user = getUsers(optionalUsers);
            log.info(user.getName() + "(" + user.getKakaoId() + ")" + " 로그인");
            Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUsersId(user.getId());
            optionalRefreshToken.ifPresent(userTest -> deleteUsersRefreshToken(userTest.getRefreshToken()));
            refreshTokenRepository.flush();  // 삭제 후 즉시 플러시
        } else {
            log.info(kakaoUserInfo.getKakao_account().getProfile().getNickname() + "(" + kakaoUserInfo.getId() + ")" + " 회원가입");
            user = userSignUp(kakaoUserInfo);
        }

        String newRefreshToken = getRefreshTokenFromJwtProvider();
        saveUserRefreshToken(newRefreshToken, user);

        return KakaoSignUpResponseDto.of(user, getAccessTokenFromJwtProvider(user.getId()), newRefreshToken);
    }

    public void deleteUsersRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
//        refreshTokenRepository.deleteByUsersId(user.getId());
    }


    @Override
    @Transactional
    public ReissueResponseDto reissue(ReissueRequestDto dto) {

        Long userId = Long.parseLong(jwtProvider.decodeJwtPayloadSubject(dto.accessToken()));

        jwtProvider.validateRefreshToken(dto.refreshToken()); //이게 refreshToken인지를 확인

        RefreshToken oldRefreshToken = validateUsersIdAndRefreshToken(dto.refreshToken(), userId); //db에 저장된 유저의 refreshToken인지를 확인

        String accessToken = getAccessTokenFromJwtProvider(userId);
        String refreshToken = getRefreshTokenFromJwtProvider();

        oldRefreshToken.updateRefreshToken(refreshToken);

        return ReissueResponseDto.of(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void logOut(LogOutRequestDto dto) {
        Long userId = jwtProvider.getUserId(dto.accessToken());

        RefreshToken refreshToken = validateUsersIdAndRefreshToken(dto.refreshToken(), userId);
        deleteUsersRefreshToken(refreshToken.getRefreshToken());
    }

    private Users getUsers(Optional<Users> optionalUsers) {
        return optionalUsers.get();
    }

    private Users userSignUp(KakaoUserInfoResponseDto kakaoUserInfo) {
        return userRepository.save(Users.toEntity(kakaoUserInfo));
    }

    private String getAccessTokenFromJwtProvider(Long userId) {
        return jwtProvider.createAccessToken(userId);
    }

    private String getRefreshTokenFromJwtProvider() {
        return jwtProvider.createRefreshToken();
    }


    private RefreshToken validateUsersIdAndRefreshToken(String refreshToken, Long userId) {
        return refreshTokenRepository.findByRefreshTokenAndUsersId(refreshToken, userId)
                .orElseThrow(() -> new JwtException(REFRESH_TOKEN_NOT_MATCH));
    }

    private void saveUserRefreshToken(String userRefreshToken, Users users) {
        refreshTokenRepository.save(RefreshToken.toEntity(userRefreshToken, users));
    }

    private void validateNotAlreadySignIn(Long kakaoId) {
        if (userRepository.existsByKakaoId(kakaoId)) throw new UserException(DUPLICATE_KAKAO_ID);
    }

    private Optional<Users> validateUserAlreadySignIn(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }

}
