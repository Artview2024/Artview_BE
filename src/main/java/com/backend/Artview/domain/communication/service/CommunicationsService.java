package com.backend.Artview.domain.communication.service;

import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.communication.dto.response.CommunicationRetrieveResponseDto;

public interface CommunicationsService {

    //소통 기록 불러오기
    CommunicationRetrieveResponseDto retrieveMyReviews(Long reviewId, Long userId);

    //소통 기록 게시하기(저장하기)
    Long saveCommunications(CommunicationSaveRequestDto dto, Long userId);
}