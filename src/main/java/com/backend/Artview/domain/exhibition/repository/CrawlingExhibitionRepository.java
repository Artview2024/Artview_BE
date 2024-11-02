package com.backend.Artview.domain.exhibition.repository;

import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrawlingExhibitionRepository extends JpaRepository<CrawlingExhibition, Long> {

    List<CrawlingExhibition> findAllByProgressType(String number);

    Slice<CrawlingExhibition> findCrawlingExhibitionTopByProgressTypeOrderByStartDateDesc(PageRequest pageRequest, String code);

    @Query(
            "SELECT ce FROM CrawlingExhibition ce WHERE ce.id < :cursor AND ce.progressType = :progressType"
    )
    Slice<CrawlingExhibition> findCrawlingExhibitionByCursorTopByAndProgressTypeOrderByStartDateDesc(@Param(value = "cursor")Long cursor, PageRequest pageRequest, String progressType);
}
