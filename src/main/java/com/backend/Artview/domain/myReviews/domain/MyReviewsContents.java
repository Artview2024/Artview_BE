package com.backend.Artview.domain.myReviews.domain;

import com.backend.Artview.domain.myReviews.dto.request.ModifyRequestArtList;
import com.backend.Artview.domain.myReviews.dto.request.SaveRequestArtList;
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

    public static <T> MyReviewsContents toEntity(MyReviews myReviews, T artList) {

        if (artList instanceof SaveRequestArtList) {
            SaveRequestArtList saveArtList = (SaveRequestArtList) artList;
            return MyReviewsContents.builder()
                    .artTitle(saveArtList.getTitle())
                    .artist(saveArtList.getArtist())
                    .note(saveArtList.getContents())
                    .myReviews(myReviews)
                    .build();
        } else {
            ModifyRequestArtList modifyArtList = (ModifyRequestArtList) artList;
            return MyReviewsContents.builder()
                    .artTitle(modifyArtList.getTitle())
                    .artist(modifyArtList.getArtist())
                    .note(modifyArtList.getContents())
                    .myReviews(myReviews)
                    .build();
        }
    }

    public void belongsToMyReviews(MyReviews myReviews) {
        this.myReviews = myReviews;
    }

    public void addImages(MyExhibitionImages myExhibitionImages) {
        this.myExhibitionImage = myExhibitionImages;
    }

    public void updateMyReviewsContents(ModifyRequestArtList artList){
        this.artTitle = artList.getTitle();
        this.artist = artList.getArtist();
        this.note = artList.getContents();
    }

}
