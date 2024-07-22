package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.domain.MyReviews.dto.ArtList;
import com.backend.Artview.global.domain.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    @OneToOne(mappedBy = "myReviewsContents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MyExhibitionImages myExhibitionImage;

    public static MyReviewsContents toEntity(MyReviews myReviews, ArtList artList) {
        return MyReviewsContents.builder()
                .artTitle(artList.title())
                .artist(artList.artist())
                .note(artList.contents())
                .myReviews(myReviews)
                .build();
    }

    public void belongsToMyReviews(MyReviews myReviews) {
        this.myReviews = myReviews;
    }

    public void addImages(MyExhibitionImages myExhibitionImages) {
        this.myExhibitionImage = myExhibitionImages;
    }
}
