package com.backend.Artview.domain.exhibition.dto.response;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
public record ExhibitionResponseDto(
        List<ExhibitionInfo> exhibitionInfos,
        boolean hasNext,
        int numberOfElements,
        Long nextCursor
) {

    public static ExhibitionResponseDto of(List<ExhibitionInfo> dtoList, Slice<CrawlingExhibition> exhibitionInfos, Long nextCursor){
        return ExhibitionResponseDto.builder()
                .exhibitionInfos(dtoList)
                .hasNext(exhibitionInfos.hasNext())
                .numberOfElements(exhibitionInfos.getNumberOfElements())
                .nextCursor(nextCursor)
                .build();
    }

}
