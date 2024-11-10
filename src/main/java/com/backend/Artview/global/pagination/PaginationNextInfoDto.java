package com.backend.Artview.global.pagination;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaginationNextInfoDto{
        public boolean hasNext;
        public int numberOfElements;
        public Long nextCursor;

    public static PaginationNextInfoDto of(boolean hasNext, int numberOfElements, Long nextCursor) {
        return PaginationNextInfoDto.builder()
                .hasNext(hasNext)
                .numberOfElements(numberOfElements)
                .nextCursor(nextCursor)
                .build();
    }

}
