package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MyReviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyReviews extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_reviwes_id", unique = true)
    private Long id;

    @Column(name = "art_title", nullable = false)
    private String artTitle;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "note", nullable = false)
    private String note;

    @OneToMany(mappedBy = "Reviews")
    private List<MyExhibitionImages> myExhibitionImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "visited_exhibitions_id")
    private VisitedExhibitions visitedExhibitions;
}
