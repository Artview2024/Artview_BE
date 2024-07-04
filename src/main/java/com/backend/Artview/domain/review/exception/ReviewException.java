package com.backend.Artview.domain.review.exception;

import com.backend.Artview.global.exception.ApplicationException;
import lombok.Getter;

@Getter
public class ReviewException extends ApplicationException {
    private final ReviewErrorCode reviewsErrorCode;

    public ReviewException(ReviewErrorCode reviewsErrorCode) {
        super(reviewsErrorCode);
        this.reviewsErrorCode = reviewsErrorCode;
    }
}