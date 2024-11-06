package com.backend.Artview.domain.exhibition.exception;

import com.backend.Artview.global.exception.ApplicationException;
import lombok.Getter;

@Getter
public class ExhibitionException extends ApplicationException {
    private final ExhibitionErrorCode exhibitionErrorCode;

    public ExhibitionException(ExhibitionErrorCode exhibitionErrorCode) {
        super(exhibitionErrorCode);
        this.exhibitionErrorCode = exhibitionErrorCode;
    }

}
