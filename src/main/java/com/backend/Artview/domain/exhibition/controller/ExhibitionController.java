package com.backend.Artview.domain.exhibition.controller;

import com.backend.Artview.domain.exhibition.dto.response.ExhibitionSearchKeywordResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailInfoResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailReviewResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionResponseDto;
import com.backend.Artview.domain.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exhibition")
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @GetMapping("/free/{cursor}")
    public ExhibitionResponseDto findFreeExhibition(@PathVariable Long cursor) {
        return exhibitionService.findFreeExhibition(cursor);
    }

    @GetMapping("/ongoing/{cursor}")
    public ExhibitionResponseDto findOngoingExhibition(@PathVariable Long cursor) {
        return exhibitionService.findOngoingExhibition(cursor);
    }

    @GetMapping("/detail/info/{exhibitionId}")
    public ExhibitionDetailInfoResponseDto findExhibitionDetailInfo(@PathVariable Long exhibitionId) {
        return exhibitionService.findExhibitionDetailInfo(exhibitionId);
    }

    @GetMapping("/detail/review/{exhibitionId}")
    public List<ExhibitionDetailReviewResponseDto> findExhibitionDetailReview(@PathVariable Long exhibitionId) {
       return exhibitionService.findExhibitionDetailReview(exhibitionId);
    }

    @GetMapping("/search/{keyword}/{cursor}")
    public ExhibitionSearchKeywordResponseDto searchExhibitionInfoByKeyword(@PathVariable(name = "keyword") String keyword, @PathVariable(name = "cursor") Long cursor){
        return exhibitionService.searchExhibitionInfoByKeyword(keyword, cursor);
    }
}
