package com.backend.Artview.domain.myReviews.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MyReviewsErrorCode implements BaseErrorCode {

    MY_REVIEWS_NOT_FOUND(HttpStatus.NOT_FOUND,404,"ReviewId에 해당하는 리뷰를 찾을 수 없습니다."),
    USER_MY_REVIEWS_NOT_FOUND(HttpStatus.NOT_FOUND,404,"사용자가 작성한 리뷰 중 해당 ReviewId에 해당하는 리뷰를 찾을 수 없습니다."),
    IMAGE_TYPE_INCORRECT(HttpStatus.NOT_FOUND,404,"이미지 타입은 String 또는 MultipartFile만 가능합니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;
}
