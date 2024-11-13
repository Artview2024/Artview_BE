package com.backend.Artview.domain.exhibition.service;

import com.backend.Artview.domain.exhibition.dto.response.*;

import java.util.List;

public interface ExhibitionService {
    ExhibitionResponseDto findOngoingExhibition(Long cursor);

    ExhibitionResponseDto findFreeExhibition(Long cursor);

    ExhibitionDetailInfoResponseDto findExhibitionDetailInfo(Long exhibitionId);

    List<ExhibitionDetailReviewResponseDto> findExhibitionDetailReview(Long exhibitionId);

    ExhibitionSearchKeywordResponseDto searchExhibitionInfoByKeyword(String keyword, Long cursor);

    ExhibitionResponseDto findOnlineExhibition(Long cursor);

    ExhibitionAverageResDto searchExhibitionAverage(Long exhibitionId);
}
