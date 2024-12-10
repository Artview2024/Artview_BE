package com.backend.Artview.domain.graduationExhibition.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record GraduationCommentResDto(
        List<String> comments
) {
    public static GraduationCommentResDto of(List<String> comments){
        return GraduationCommentResDto.builder()
                .comments(comments)
                .build();
    }
}
