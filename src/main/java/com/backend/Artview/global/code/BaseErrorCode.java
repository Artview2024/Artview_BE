package com.backend.Artview.global.code;

import com.backend.Artview.global.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;


public interface BaseErrorCode {

    HttpStatus getHttpStatus();

    int getStatusCode();

    String getMessage();
}
