package com.backend.Artview.domain.graduationExhibition.Service;

import com.backend.Artview.domain.graduationExhibition.Entity.GraduationExhibitionComment;
import com.backend.Artview.domain.graduationExhibition.dto.req.GraduationCommentReqDto;
import com.backend.Artview.domain.graduationExhibition.dto.res.GraduationCommentResDto;
import com.backend.Artview.domain.graduationExhibition.repository.GraduationExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraduationExhibitionService {

    private final GraduationExhibitionRepository graduationExhibitionRepository;

    public void saveComment(GraduationCommentReqDto resDto) {
        graduationExhibitionRepository.save(GraduationExhibitionComment.toEntity(resDto));
    }

    public GraduationCommentResDto getComment() {
        List<String> comments = graduationExhibitionRepository.findAll().stream().map(GraduationExhibitionComment::getComment).toList();
        return GraduationCommentResDto.of(comments);
    }
}
