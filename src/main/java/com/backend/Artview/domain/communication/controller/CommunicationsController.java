package com.backend.Artview.domain.communication.controller;

import com.backend.Artview.domain.communication.dto.request.CommunicationsCommentRequestDto;
import com.backend.Artview.domain.communication.dto.request.LikeRequestDto;
import com.backend.Artview.domain.communication.dto.response.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.communication.dto.response.DetailCommunicationsCommentResponseDto;
import com.backend.Artview.domain.communication.dto.response.DetailCommunicationsContentResponseDto;
import com.backend.Artview.domain.communication.service.CommunicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //소통 기록 상세보기 - 내용 조회
    @GetMapping("/content/{communicationsId}")
    public DetailCommunicationsContentResponseDto detailCommunicationsContent(@PathVariable Long communicationsId){
        return communicationsService.detailCommunicationsContent(communicationsId,userId);
    }

    //소통 기록 상세보기 - 댓글 조회 - 중복 등록하지 못하도록 로직 수정 필요
    @GetMapping("/comment/{communicationsId}")
    public List<DetailCommunicationsCommentResponseDto> detailCommunicationsComment(@PathVariable Long communicationsId){
        return communicationsService.detailCommunicationsComment(communicationsId,userId);
    }

    //소통 좋아요 등록하기
    @PostMapping("/like")
    public void communicationsSaveLike(@RequestBody LikeRequestDto dto){
        communicationsService.toggleLike(dto, userId);
    }

    //소통 좋아요 삭제하기
    @DeleteMapping("/like")
    public void communicationsDeleteLike(@RequestBody LikeRequestDto dto){
        communicationsService.toggleLike(dto, userId);
    }

    //소통 페이지 둘러보기 - 전체
    @GetMapping("/main/all/{cursor}") //cursor : 페이지번호
    public void findAllCommunications(@PathVariable int cursor){
        communicationsService.findAllCommunications(cursor, userId);
    }
}
