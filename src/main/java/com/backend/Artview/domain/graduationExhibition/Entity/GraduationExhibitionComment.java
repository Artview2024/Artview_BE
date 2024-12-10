package com.backend.Artview.domain.graduationExhibition.Entity;

import com.backend.Artview.domain.graduationExhibition.dto.req.GraduationCommentReqDto;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GraduationExhibitionComment")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class GraduationExhibitionComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduation_exhibition_id")
    private Long id;

    private String comment;

    public static GraduationExhibitionComment toEntity(GraduationCommentReqDto resDto) {
        return GraduationExhibitionComment.builder()
                .comment(resDto.comment())
                .build();
    }
}
