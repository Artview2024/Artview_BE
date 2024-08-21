package com.backend.Artview.domain.myReviews.exception;

import com.backend.Artview.global.exception.ApplicationException;
import lombok.Getter;

@Getter
public class MyReviewsException extends ApplicationException {
    private final MyReviewsErrorCode reviewsErrorCode;

    public MyReviewsException(MyReviewsErrorCode reviewsErrorCode) {
        super(reviewsErrorCode);
        this.reviewsErrorCode = reviewsErrorCode;
    }
}