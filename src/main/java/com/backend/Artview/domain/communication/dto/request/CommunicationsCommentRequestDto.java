package com.backend.Artview.domain.communication.dto.request;

public record CommunicationsCommentRequestDto(
        Long myReviewId,		//전시기록 id
        String comment	//댓글 내용
) {
}
