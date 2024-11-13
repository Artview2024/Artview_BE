package com.backend.Artview.domain.exhibition.dto.response;

import lombok.Builder;

@Builder
public record ExhibitionAverageResDto(
        String average,
        int participantsNumber
) {
    public static ExhibitionAverageResDto of(String average, int participantsNumber){
        return ExhibitionAverageResDto.builder()
                .average(average)
                .participantsNumber(participantsNumber)
                .build();
    }

}
