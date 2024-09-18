package com.backend.Artview.domain.communication.dto.response;

import com.backend.Artview.domain.communication.domain.Communications;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
public record CommunicationsMainResponseDto(

    List<DetailCommunicationsContentResponseDto> detailCommunicationsContentResponseDtoList,
    boolean hasNext,
    int numberOfElements,
    Long nextCursor //다음 커서에 이 값을 담아서 보내주면 됨

) {
    public static CommunicationsMainResponseDto of(List<DetailCommunicationsContentResponseDto> dtoList, Slice<Communications> communicationsList,Long nextCursor) {
        return CommunicationsMainResponseDto.builder()
                .detailCommunicationsContentResponseDtoList(dtoList)
                .hasNext(communicationsList.hasNext())
                .numberOfElements(communicationsList.getNumberOfElements())
                .nextCursor(nextCursor)
                .build();
    }
}
