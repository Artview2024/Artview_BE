package com.backend.Artview.domain.exhibition.controller;

import com.backend.Artview.domain.exhibition.dto.response.ExhibitionResponseDto;
import com.backend.Artview.domain.exhibition.service.ExhibitionService;
import com.backend.Artview.domain.exhibition.service.ExhibitionServiceImpl;
import com.backend.Artview.domain.users.dto.response.MyPageFollowAndMyReviewsNumberInfoResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.service.UserService;
import com.backend.Artview.global.customAnnotation.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exhibition")
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;
    private final UserService userService;

    @GetMapping("/upcoming/{cursor}")
    public ExhibitionResponseDto findUpcomingExhibition(@PathVariable Long cursor) {
        return exhibitionService.findUpcomingExhibition(cursor);
    }

    @GetMapping("/ongoing/{cursor}")
    public ExhibitionResponseDto findOngoingExhibition(@PathVariable Long cursor) {
        return exhibitionService.findOngoingExhibition(cursor);
    }
}
