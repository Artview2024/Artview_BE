package com.backend.Artview.domain.communication.dto.response;

import com.backend.Artview.domain.communication.domain.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record DetailCommunicationsCommentResponseDto(
        Long writerId,
        LocalDateTime createDate,
        String writername,
        String writerImage,
        String content, //가장 상위 댓글
        List<DetailCommunicationsCommentResponseDto> replies //대댓글
) {

    public static DetailCommunicationsCommentResponseDto of(Comment comment) {
        return DetailCommunicationsCommentResponseDto.builder()
                .writerId(comment.getUsers().getId())
                .createDate(comment.getCreateDate())
                .writername(comment.getUsers().getName())
                .writerImage(comment.getUsers().getUserImage())
                .content(comment.getContent())
                .replies(new ArrayList<>())
                .build();
    }
}
