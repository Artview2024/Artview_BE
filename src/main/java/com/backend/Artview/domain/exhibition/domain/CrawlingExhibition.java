package com.backend.Artview.domain.exhibition.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Table(name = "CrawlingExhibition")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicUpdate
public class CrawlingExhibition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crawling_exhibition_id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "finish_date")
    private String finishDate;

    @Column(name = "location")
    private String location;

    @Column(name = "location_link", columnDefinition="varchar(500)")
    private String locationLink;

    @Column(name = "main_image_url", columnDefinition="varchar(500)")
    private String mainImageUrl;

    @Column(name = "operating_hours")
    private String operatingHours;

    @Column(name = "progress_type")
    private String progressType;

    public void updateProgress(String progressType) {
        this.progressType = progressType;
    }
}
