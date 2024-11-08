package com.backend.Artview.domain.exhibition.dto.response;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import lombok.Builder;

import java.util.List;

import static com.backend.Artview.domain.exhibition.domain.ExhibitionType.FREE;
import static com.backend.Artview.domain.exhibition.domain.ExhibitionType.ONGOING;
import static com.backend.Artview.global.util.StringUtil.removeTextFromSentence;

@Builder
public record ExhibitionDetailInfoResponseDto(
        ExhibitionInfo exhibitionInfo,
        List<String> operatingHours,
        boolean isOngoing,
        String locationLink
) {
    public static ExhibitionDetailInfoResponseDto of(CrawlingExhibition crawlingExhibition) {
        return ExhibitionDetailInfoResponseDto.builder()
                .exhibitionInfo(ExhibitionInfo.of(crawlingExhibition))
                .operatingHours(crawlingExhibition.getOperatingHours().isEmpty() ?
                        null : removeTextFromSentence(crawlingExhibition.getOperatingHours(), "\n"))
                .isOngoing(checkExhibitionProgressType(crawlingExhibition.getProgressType()))
                .locationLink(crawlingExhibition.getLocationLink())
                .build();
    }

    private static boolean checkExhibitionProgressType(String progressType) {
        return (progressType.equals(ONGOING.getCode()) || progressType.equals(FREE.getCode()));
    }

    private static boolean checkOperatingHours(String operatingHours){
        return (operatingHours.isEmpty());
    }
}
