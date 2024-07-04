package com.backend.Artview.domain.review.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VisitedExhibitions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VisitedExhibitions extends BaseEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "visited_exhibitions_id")
    private Long id;
}
