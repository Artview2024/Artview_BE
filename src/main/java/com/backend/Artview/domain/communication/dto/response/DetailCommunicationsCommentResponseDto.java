package com.backend.Artview.domain.communication.dto.response;

import com.backend.Artview.domain.communication.domain.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record DetailCommunicationsCommentResponseDto(
        Long userId,
        LocalDateTime createDate,
        String username,
        String userImage,
        String content, //가장 상위 댓글
        List<DetailCommunicationsCommentResponseDto> replies //대댓글
) {

    public static DetailCommunicationsCommentResponseDto of(Comment comment) {
        return DetailCommunicationsCommentResponseDto.builder()
                .userId(comment.getUsers().getId())
                .createDate(comment.getCreateDate())
                .username(comment.getUsers().getName())
                .userImage(comment.getUsers().getUserImage())
                .content(comment.getContent())
                .replies(new ArrayList<>())
                .build();
    }
}
