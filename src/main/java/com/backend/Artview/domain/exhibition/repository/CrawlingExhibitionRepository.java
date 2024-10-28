package com.backend.Artview.domain.exhibition.repository;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlingExhibitionRepository extends JpaRepository<CrawlingExhibition, Long> {

    List<CrawlingExhibition> findAllByProgressType(String number);
}
