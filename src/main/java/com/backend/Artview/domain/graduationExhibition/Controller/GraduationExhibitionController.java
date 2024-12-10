package com.backend.Artview.domain.graduationExhibition.Controller;

import com.backend.Artview.domain.graduationExhibition.Service.GraduationExhibitionService;
import com.backend.Artview.domain.graduationExhibition.dto.req.GraduationCommentReqDto;
import com.backend.Artview.domain.graduationExhibition.dto.res.GraduationCommentResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graduation")
@RequiredArgsConstructor
public class GraduationExhibitionController {
    private final GraduationExhibitionService graduationExhibitionService;
    @PostMapping
    public void graduationComment(@RequestBody GraduationCommentReqDto resDto){
        graduationExhibitionService.saveComment(resDto);
    }

    @GetMapping
    public GraduationCommentResDto graduationComment(){
        return graduationExhibitionService.getComment();
    }
}
