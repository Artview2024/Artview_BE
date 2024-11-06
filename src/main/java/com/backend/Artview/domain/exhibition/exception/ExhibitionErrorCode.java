package com.backend.Artview.domain.exhibition.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExhibitionErrorCode implements BaseErrorCode {
    EXHIBITION_NOT_FOUND(HttpStatus.NOT_FOUND,404,"id와 일치하는 전시정보를 찾을 수 없습니다."),
    EXHIBITION_REVIEWS_NOT_FOUND(HttpStatus.NOT_FOUND,404,"전시 id와 일치하는 리뷰를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;

}
