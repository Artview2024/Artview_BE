package com.backend.Artview.domain.MyReviews.domain;

import com.backend.Artview.domain.MyReviews.dto.request.MyReviewsSaveReqeustDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;

@Entity
@Table(name = "MyReviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Slf4j
public class MyReviews extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_reviwes_id", unique = true)
    private Long id;

    @Column(name = "exhibitions_title", nullable = false)
    private String exhibitionsTitle;

    @Column(name = "exhibitions_location", nullable = false)
    private String exhibitionsLocation;

    @Column(name = "visited_date", nullable = false)
    private String visitedDate;

    @Column(name = "grade", nullable = false)
    private float grade;

    @Column(name = "main_image_url", nullable = false)
    private String mainImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "myReviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MyReviewsContents> myReviewsContents = new ArrayList<>();

    public static MyReviews toEntity(MyReviewsSaveReqeustDto requestDto, Users users) {
        return MyReviews.builder()
                .exhibitionsTitle(requestDto.name())
                .exhibitionsLocation(requestDto.gallery())
                .visitedDate(requestDto.date())
                .grade(Float.parseFloat(requestDto.rating()))
                .mainImageUrl(requestDto.mainImage())
                .users(users)
                .myReviewsContents(new ArrayList<>())
                .build();
    }

    public void addContents(MyReviewsContents myReviewsContents) {
        this.myReviewsContents.add(myReviewsContents);
    }
}
