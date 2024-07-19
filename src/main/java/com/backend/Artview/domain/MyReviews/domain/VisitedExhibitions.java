package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VisitedExhibitions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VisitedExhibitions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visited_exhibitions_id")
    private Long id;

    @Column(name = "visited_date")
    private String visitedDate;

    @Column(name = "grade")
    private int grade;

    @OneToMany(mappedBy = "VisitedExhibitions")
    private List<MyReviews> myReviews = new ArrayList<>();

}
