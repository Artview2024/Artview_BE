package com.backend.Artview.global.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    public PageRequest createPageRequest(int defaultPageSize, String sort) {
        return PageRequest.of(0, defaultPageSize, Sort.by(sort).descending());
    }
}
