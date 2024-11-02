package com.backend.Artview.domain.users.dto.request;

import java.util.List;

public record SaveUsersInterestRequestDto(
        List<String> usersInterest
) {

}
