package com.backend.Artview.domain.exhibition.controller;

import com.backend.Artview.domain.exhibition.dto.response.ExhibitionResponseDto;
import com.backend.Artview.domain.exhibition.service.ExhibitionService;
import com.backend.Artview.domain.exhibition.service.ExhibitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exhibition")
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @GetMapping("/upcoming/{cursor}")
    public ExhibitionResponseDto findUpcomingExhibition(@PathVariable Long cursor) {
        return exhibitionService.findUpcomingExhibition(cursor);
    }

    @GetMapping("/ongoing/{cursor}")
    public ExhibitionResponseDto findOngoingExhibition(@PathVariable Long cursor) {
        return exhibitionService.findOngoingExhibition(cursor);
    }


}
