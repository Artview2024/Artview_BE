package com.backend.Artview.domain.exhibition.dto.response;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import com.backend.Artview.global.pagination.PaginationNextInfoDto;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
public record ExhibitionResponseDto(
        List<ExhibitionInfo> exhibitionInfos,
        PaginationNextInfoDto nextInfoDto

) {

    public static ExhibitionResponseDto of(List<ExhibitionInfo> dtoList, Slice<CrawlingExhibition> exhibitionInfos, Long nextCursor){
        return ExhibitionResponseDto.builder()
                .exhibitionInfos(dtoList)
                .nextInfoDto(PaginationNextInfoDto.of(exhibitionInfos.hasNext(),exhibitionInfos.getNumberOfElements(),nextCursor))
                .build();
    }

}
