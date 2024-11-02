package com.backend.Artview.domain.exhibition.service;

import com.backend.Artview.domain.exhibition.dto.response.ExhibitionResponseDto;

public interface ExhibitionService {
    ExhibitionResponseDto findOngoingExhibition(Long cursor);

    ExhibitionResponseDto findFreeExhibition(Long cursor);
}
