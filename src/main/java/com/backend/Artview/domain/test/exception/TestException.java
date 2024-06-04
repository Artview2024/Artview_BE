package com.backend.Artview.domain.test.exception;

import com.backend.Artview.global.exception.ApplicationException;
import lombok.Getter;

@Getter
public class TestException extends ApplicationException {
    private final TestErrorCode testErrorCode;

    public TestException(TestErrorCode testErrorCode) {
        super(testErrorCode);
        this.testErrorCode = testErrorCode;
    }
}
