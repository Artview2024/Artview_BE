package com.backend.Artview.domain.exhibition.service;

import com.backend.Artview.domain.exhibition.dto.response.ExhibitionSearchKeywordResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailInfoResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailReviewResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionResponseDto;

import java.util.List;

public interface ExhibitionService {
    ExhibitionResponseDto findOngoingExhibition(Long cursor);

    ExhibitionResponseDto findFreeExhibition(Long cursor);

    ExhibitionDetailInfoResponseDto findExhibitionDetailInfo(Long exhibitionId);

    List<ExhibitionDetailReviewResponseDto> findExhibitionDetailReview(Long exhibitionId);

    ExhibitionSearchKeywordResponseDto searchExhibitionInfoByKeyword(String keyword, Long cursor);
}
