package com.backend.Artview.domain.Communication.controller;

import com.backend.Artview.domain.Communication.dto.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.Communication.service.CommunicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/communications")
@RequiredArgsConstructor
public class CommunicationsController {

    private final CommunicationsService communicationsService;
    //소통 기록 불러오기
    @GetMapping("/retrieve/{reviewsId}")
    public CommunicationRetrieveResponseDto retrieve(@PathVariable Long reviewsId) {
        return communicationsService.retrieveMyReviews(reviewsId);
    }

}
