package com.backend.Artview.domain.exhibition.dto.response;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import lombok.Builder;

import java.util.List;

import static com.backend.Artview.domain.exhibition.domain.ExhibitionType.*;
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
                .operatingHours(checkOperatingHours(crawlingExhibition))
                .isOngoing(checkExhibitionProgressType(crawlingExhibition.getProgressType()))
                .locationLink(crawlingExhibition.getLocationLink())
                .build();
    }

    private static boolean checkExhibitionProgressType(String progressType) {
        return (!progressType.equals(COMPLETED.getCode()));
    }

    private static List<String> checkOperatingHours(CrawlingExhibition crawlingExhibition){
        if (crawlingExhibition.getOperatingHours() == null) return null;
        else return removeTextFromSentence(crawlingExhibition.getOperatingHours(), "\n");
    }
}
