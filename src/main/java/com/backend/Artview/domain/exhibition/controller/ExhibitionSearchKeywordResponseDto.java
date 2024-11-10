package com.backend.Artview.domain.exhibition.controller;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailInfoResponseDto;
import com.backend.Artview.global.pagination.PaginationNextInfoDto;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
public record ExhibitionSearchKeywordResponseDto(
        List<ExhibitionDetailInfoResponseDto> exhibitionDetailInfo,
        PaginationNextInfoDto paginationNextInfoDto
) {
    public static ExhibitionSearchKeywordResponseDto of(List<ExhibitionDetailInfoResponseDto> exhibitionDetailInfo, Slice<CrawlingExhibition> crawlingExhibitionList, Long nextCursor){
        return ExhibitionSearchKeywordResponseDto.builder()
                .exhibitionDetailInfo(exhibitionDetailInfo)
                .paginationNextInfoDto(PaginationNextInfoDto.of(crawlingExhibitionList.hasNext(), crawlingExhibitionList.getNumberOfElements(),nextCursor))
                .build();
    }
}
