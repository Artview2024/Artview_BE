package com.backend.Artview.domain.communication.dto.request;

public record CommunicationsCommentRequestDto(
        Long communicationsId,//소통 게시물 id
        String content,	//댓글 내용
        Long parentContentId //부모 댓글 id -> 부모 댓글이 없다면 null
) {
}
