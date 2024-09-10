package com.backend.Artview.domain.communication.service;

import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.communication.dto.request.CommunicationsCommentRequestDto;
import com.backend.Artview.domain.communication.dto.request.LikeRequestDto;
import com.backend.Artview.domain.communication.dto.response.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.communication.dto.response.DetailCommunicationsCommentResponseDto;
import com.backend.Artview.domain.communication.dto.response.DetailCommunicationsContentResponseDto;

import java.util.List;

public interface CommunicationsService {

    //소통 기록 불러오기
    CommunicationRetrieveResponseDto retrieveMyReviews(Long reviewId, Long userId);

    //소통 기록 게시하기(저장하기)
    Long saveCommunications(CommunicationSaveRequestDto dto, Long userId);

    Long saveComment(CommunicationsCommentRequestDto dto, Long userId);

    DetailCommunicationsContentResponseDto detailCommunicationsContent(Long communicationsId, Long userId);

    List<DetailCommunicationsCommentResponseDto> detailCommunicationsComment(Long communicationsId, Long userId);

    void likeSave(LikeRequestDto dto, Long userId);

    void likeDelete(LikeRequestDto dto, Long userId);
}
