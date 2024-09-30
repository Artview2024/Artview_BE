package com.backend.Artview.domain.auth.dto.request;

public record ReissueRequestDto(

        String accessToken,
        String refreshToken

) {
}
