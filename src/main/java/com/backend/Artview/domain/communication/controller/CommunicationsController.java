package com.backend.Artview.domain.communication.controller;

import com.backend.Artview.domain.communication.domain.Comment;
import com.backend.Artview.domain.communication.dto.request.CommunicationsCommentRequestDto;
import com.backend.Artview.domain.communication.dto.response.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.communication.service.CommunicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/communications")
@RequiredArgsConstructor
public class CommunicationsController {

    private final CommunicationsService communicationsService;
    private final Long userId = 10001L; //로그인 구현전까지 임시 사용

    //소통 기록 불러오기
    @GetMapping("/retrieve/{reviewsId}")
    public CommunicationRetrieveResponseDto retrieve(@PathVariable Long reviewsId) {
        return communicationsService.retrieveMyReviews(reviewsId,userId);
    }

    //소통 기록 게시하기(저장하기)
    @PostMapping("/save")
    public Long save(@RequestBody CommunicationSaveRequestDto dto) {
        return communicationsService.saveCommunications(dto, userId);
    }

    //소통 댓글 등록하기
    @PostMapping("/comment")
    public Long comment(@RequestBody CommunicationsCommentRequestDto dto){
        return communicationsService.saveComment(dto,userId);
    }

}
