package com.backend.Artview.domain.users.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record ModifyMyPageInfoRequestDto<T>(
        String userName,
        T userImageUrl,
        List<String> usersInterest
) {
}
