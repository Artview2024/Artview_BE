package com.backend.Artview.domain.Communication.service;

import com.backend.Artview.domain.Communication.dto.CommunicationRetrieveResponseDto;

public interface CommunicationsService {

    //소통 기록 불러오기
    CommunicationRetrieveResponseDto retrieveMyReviews(Long reviewId);
}
