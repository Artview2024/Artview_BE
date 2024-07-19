package com.backend.Artview.domain.MyReviews.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MyReviewsErrorCode implements BaseErrorCode {

    MY_REVIEWS_NOT_FOUND(HttpStatus.NOT_FOUND,404,"ReviewId에 해당하는 리뷰를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;
}
