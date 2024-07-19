package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.domain.users.domain.Users;
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

    @Column(name = "exhibitions_title", nullable = false)
    private String exhibitionsTitle;

    @Column(name = "visited_date", nullable = false)
    private String visitedDate;

    @Column(name = "grade", nullable = false)
    private float grade;

    @Column(name = "main_image_url", nullable = false)
    private String main_image_url;

    @OneToMany(mappedBy = "myReviews",fetch = FetchType.LAZY)
    private List<MyExhibitionImages> myExhibitionImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "MyReviews")
    private List<MyReviewsContents> myReviewsContents= new ArrayList<>();
}
