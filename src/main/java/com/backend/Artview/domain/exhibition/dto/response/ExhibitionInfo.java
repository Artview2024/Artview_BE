package com.backend.Artview.domain.exhibition.dto.response;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import lombok.Builder;

@Builder
public record ExhibitionInfo(
        Long exhibitionId,
        String mainImageUrl, //메인 이미지
        String title,
        String startDate,
        String finishDate,
        String location
) {
    public static ExhibitionInfo of(CrawlingExhibition data) {
        return ExhibitionInfo.builder()
                .exhibitionId(data.getId())
                .mainImageUrl(data.getMainImageUrl())
                .title(data.getTitle())
                .startDate(String.valueOf(data.getStartDate()))
                .finishDate(String.valueOf(data.getFinishDate()))
                .location(data.getLocation())
                .build();
    }
}
