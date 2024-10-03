package com.backend.Artview.global.jwt;

import com.backend.Artview.global.jwt.jwtException.JwtException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static com.backend.Artview.global.jwt.jwtException.JwtErrorCode.*;

@Slf4j
@Component
//Jwt 생성 및 유효성 검증을 하는 컴포넌트
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-expiration-hours}")
    private long accessExpirationHours;

    @Value("${jwt.refresh-expiration-hours}")
    private long refreshExpirationHours;
    private ObjectMapper objectMapper = new ObjectMapper();

    // access token 발급 method
    public String createAccessToken(Long userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setHeaderParam("type", "accessToken")
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
//                .setSubject(String.valueOf(userId))  // JWT 토큰 제목
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
                .setExpiration(Date.from(Instant.now().plus(accessExpirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                .compact(); // JWT 토큰 생성
    }

    // refresh token 발급 method
    public String createRefreshToken() {
        return Jwts.builder()
                .setHeaderParam("type", "refreshToken")
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .setExpiration(Date.from(Instant.now().plus(refreshExpirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                .compact(); // JWT 토큰 생성
    }

    // 토큰 subject꺼내기 (유저 id)
    public Long getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }

    public String decodeJwtPayloadSubject(String oldAccessToken) {
        try {
            return objectMapper.readValue(
                    new String(Base64.getDecoder().decode(oldAccessToken.split("\\.")[1]), StandardCharsets.UTF_8),
                    Map.class
            ).get("userId").toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    // refresh 토큰 확인
    public Boolean validateRefreshToken(String refreshToken) {
        try {
             if(getHeaderFromJWT(refreshToken).get("type").toString().equals("refreshToken")) return true;
             else throw new JwtException(TOKEN_TYPE_NOT_MATCH);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            throw new JwtException(EXPIRED_JWT_REFRESH_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new JwtException(INVALID_JWT_REFRESH_TOKEN);
        }
    }

    // access 토큰 확인
    public Boolean validateAccessToken(String accessToken) {
        try {
            if (getHeaderFromJWT(accessToken).get("type").toString().equals("accessToken")) return true;
            else throw new JwtException(TOKEN_TYPE_NOT_MATCH);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            log.error("ACCESS TOKEN이 만료되었습니다. 재발급 받아주세요.");
            throw new JwtException(EXPIRED_JWT_ACCESS_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.error(e.getMessage());
            log.error("유효하지 않은 ACCESS TOKEN 입니다");
            throw new JwtException(INVALID_JWT_ACCESS_TOKEN);
        }
    }

    public Header getHeaderFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getHeader();
    }


}
