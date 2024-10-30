package com.backend.Artview.domain.users.dto.request;

import lombok.Builder;

@Builder
public record ModifyMyPageInfoRequestDto<T>(
        String userName,
        T userImageUrl
) {
}
