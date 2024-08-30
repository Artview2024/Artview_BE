package com.backend.Artview.domain.myReviews.domain;

import com.backend.Artview.domain.myReviews.dto.request.RequestArtList;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "art_title")
    private String artTitle;

    @Column(name = "artist")
    private String artist;

    @Column(name = "note")
    private String note;

    @Column(name = "number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "MyReviews_id")
    private MyReviews myReviews;

    @OneToOne(mappedBy = "myReviewsContents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MyExhibitionImages myExhibitionImage;

    public static MyReviewsContents toEntity(MyReviews myReviews, RequestArtList artList) {
        return MyReviewsContents.builder()
                .artTitle(artList.getTitle())
                .artist(artList.getArtist())
                .note(artList.getContents())
                .myReviews(myReviews)
                .build();
    }

    public void belongsToMyReviews(MyReviews myReviews) {
        this.myReviews = myReviews;
    }

    public void addImages(MyExhibitionImages myExhibitionImages) {
        this.myExhibitionImage = myExhibitionImages;
    }

    public void updateMyReviewsContents(RequestArtList artList){
        this.artTitle = artList.getTitle();
        this.artist = artList.getArtist();
        this.note = artList.getContents();
    }

}
