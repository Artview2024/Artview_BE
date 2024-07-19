package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.global.domain.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyReviewsContents extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_reviews_contents_id", unique = true)
    private Long id;

    @Column(name = "art_title", nullable = false)
    private String artTitle;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "note", nullable = false)
    private String note;

    @ManyToOne
    @JoinColumn(name = "MyReviews_id")
    private MyReviews myReviews;

    @OneToOne(mappedBy = "myReviewsContents")
    private MyExhibitionImages myExhibitionImage;
}
