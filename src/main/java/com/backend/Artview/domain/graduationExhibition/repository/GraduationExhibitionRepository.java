package com.backend.Artview.domain.graduationExhibition.repository;

import com.backend.Artview.domain.graduationExhibition.Entity.GraduationExhibitionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationExhibitionRepository extends JpaRepository<GraduationExhibitionComment, Long> {
}
