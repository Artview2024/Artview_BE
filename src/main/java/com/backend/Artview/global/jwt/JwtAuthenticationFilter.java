package com.backend.Artview.global.jwt;

import com.backend.Artview.global.jwt.jwtException.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.backend.Artview.global.jwt.jwtException.JwtErrorCode.UNAUTHORIZED;


//API 요청이 들어왔을때 컨트롤러로 넘어가기전 토큰을 확인해주는 필터함수

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private static final List<RequestMatcher> whiteUrlMatchers = Arrays.asList(
            //로그인 관련 URI
            new AntPathRequestMatcher("/api/auth/**"),

            //소통 페이지 관련 URI
            new AntPathRequestMatcher("/api/communications/main/all/{cursor}"),
            new AntPathRequestMatcher("/api/communications/content/{communicationsId}"),
            new AntPathRequestMatcher("/api/communications/comment/{communicationsId}"),

            //기타 URI
            new AntPathRequestMatcher("/api/health")

    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthenticationFilter 진입");

        //whiteUrlMatcher url일 경우 jwt 인증 건너뛰기
        for (RequestMatcher requestMatcher : whiteUrlMatchers) {
            if (requestMatcher.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        log.info("requst 인증하는중");

        String accessToken = getAccessTokenFromHttpServletRequest(request); //JWT가 헤더에 있는지 판단
        jwtProvider.validateAccessToken(accessToken); //JWT 유효성 검증
        Long userId = jwtProvider.getUserId(accessToken);
        setAuthentication(request, userId); //현재 Request의 Security Context에 접근권한 설정
                    // -> Context에 이것이 추가된다는 것은 해당 요청이 필터를 거쳐 인가에 성공하여 승인된 Request라는 의미
        filterChain.doFilter(request, response);
    }

    //AccessToken 가져오기
    private String getAccessTokenFromHttpServletRequest(HttpServletRequest request) {
        log.info("getAccessTokenFromHttpServletRequest 진입");
        String accessToken = request.getHeader(AuthConstants.AUTH_HEADER);

        if (StringUtils.hasText(accessToken) && accessToken.startsWith(AuthConstants.TOKEN_TYPE)) {
            return accessToken.split(" ")[1]; //토큰 꺼내기
        }
        throw new JwtException(UNAUTHORIZED);
    }

    private void setAuthentication(HttpServletRequest request, Long userId) {
        // 권한 부여
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("USER")));
        //Detail 추가
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("[+] Token in SecurityContextHolder");
    }
}
