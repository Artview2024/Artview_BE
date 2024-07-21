package com.backend.Artview.domain.users.exception;


import com.backend.Artview.global.exception.ApplicationException;
import lombok.Getter;

@Getter
public class UserException extends ApplicationException {

    private final UserErrorCode userErrorCode;

    public UserException(UserErrorCode userErrorCode){
        super(userErrorCode);
        this.userErrorCode = userErrorCode;
    }

}
