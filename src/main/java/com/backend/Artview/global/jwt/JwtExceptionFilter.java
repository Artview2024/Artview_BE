package com.backend.Artview.global.jwt;


import com.backend.Artview.global.code.BaseErrorCode;
import com.backend.Artview.global.constant.CommonErrorCode;
import com.backend.Artview.global.exception.ErrorResponse;
import com.backend.Artview.global.exception.ForbiddenException;
import com.backend.Artview.global.jwt.jwtException.JwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.backend.Artview.global.constant.CommonErrorCode.INTERNAL_SERVER_ERROR;

/*OncePerRequestFilter : Http Request의 동일한 요청에 대해 한번만 Filtering 할 수 있게 해주는 filter
인증/인가와 같이 한번만 거쳐야 하는 로직에 많이 사용
인증,인가를 거치고 특정 url로 포워딩할 때 OncePerRequestFilter를 사용하지 않으면 요청이 들어왔으니 인증,인가 filter를 다시 실행해야 하지만
OncePerRequestFilter를 사용하면 인증,인가를 한번만 거치고 바로 다음 로직을 진행할 수 있다.*/


//JwtFilter을 통과하기전에 토큰이 만료되었거나 토큰이 비었을 경우 Exception을 발생시켜 처리하는 exceptionFilter
//요청된 API 컨트롤러로 넘어가기 전에 바로 클라이언트에게 response를 보낼 수 있음
@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtExceptionFilter 진입");
        log.info("CONNECT URL : " + request.getRequestURI());
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) { //HttpStatus.UNAUTHORIZED -> 인가
            handleJwtException(response, e);
        } catch (ForbiddenException e) { //AuthenticationException -> 인증
            handleJwtException(response, e);
        } catch (HttpClientErrorException e) {
            handleJwtException(response, e);
        } catch (Exception e) {
            handleJwtException(response, e);
        }
    }


    // Send Error Message to Client
    private void handleJwtException(HttpServletResponse response, Exception exception) throws IOException {
        log.error("handleJwtException response : " + response);
        log.error("handleJwtException 에러 : " + exception.getMessage());

        BaseErrorCode baseErrorCode;

        if (exception instanceof JwtException jwtException) {
            baseErrorCode = jwtException.getErrorCode();
        } else if (exception instanceof ForbiddenException forbiddenException) {
            baseErrorCode = forbiddenException.getErrorCode();
        } else baseErrorCode = INTERNAL_SERVER_ERROR;

        response.setStatus(baseErrorCode.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(baseErrorCode)));
    }
}
