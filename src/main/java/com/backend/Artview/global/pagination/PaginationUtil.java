package com.backend.Artview.global.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    public static final int SEARCH_DEFAULT_PAGE_SIZE = 6;
    public static final int COMMUNICATIONS_DEFAULT_PAGE_SIZE = 2;


    public static PageRequest createPageRequest(int defaultPageSize, String sort) {
        return PageRequest.of(0, defaultPageSize, Sort.by(sort).descending());
    }

    public static PageRequest createPageRequest(int defaultPageSize) {
        return PageRequest.of(0, defaultPageSize);
    }
}
