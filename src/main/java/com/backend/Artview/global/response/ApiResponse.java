package com.backend.Artview.global.response;

import com.backend.Artview.global.constant.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private final int status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) //값이 null이면 노출하지 않음
    private T data;

    public static ResponseEntity<ApiResponse> of(SuccessCode successCode) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(new ApiResponse(successCode.getStatusCode(), successCode.getMessage()));
    }

    public static <T> ResponseEntity<ApiResponse<T>> of(SuccessCode successCode, T data) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(new ApiResponse(successCode.getStatusCode(), successCode.getMessage(), data));
    }
}
