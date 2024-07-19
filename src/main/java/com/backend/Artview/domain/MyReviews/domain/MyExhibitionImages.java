package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MyExhibitionImages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyExhibitionImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_exhibitions_images_id", unique = true)
    private Long id;

    @Column(name = "my_exhibition_images_url")
    private String myExhibitionImages_Url;

    @OneToOne
    @JoinColumn(name = "MyReviewsContents_id")
    private MyReviewsContents myReviewsContents;

}
