package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MyExhibitionImages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MyExhibitionImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_exhibitions_images_id", unique = true)
    private Long id;

    @Column(name = "my_exhibition_images_url")
    private String myExhibitionImagesUrl;

    @OneToOne
    @JoinColumn(name = "MyReviewsContents_id")
    private MyReviewsContents myReviewsContents;

//    private Long myReviewsContentsId;

    public static MyExhibitionImages toEntity(String myExhibitionImagesUrl, MyReviewsContents myReviewsContents){
        return MyExhibitionImages.builder()
                .myExhibitionImagesUrl(myExhibitionImagesUrl)
                .myReviewsContents(myReviewsContents)
                .build();
    }

}
