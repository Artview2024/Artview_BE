package com.backend.Artview.domain.myReviews.domain;

import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveRequestDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Entity
@Table(name = "MyReviews")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Slf4j
public class MyReviews extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_reviwes_id", unique = true)
    private Long id;

    @Column(name = "exhibitions_title")
    private String exhibitionsTitle;

    @Column(name = "exhibitions_location")
    private String exhibitionsLocation;

    @Column(name = "visited_date")
    private String visitedDate;

    @Column(name = "grade")
    private String grade;

    @Column(name = "main_image_url")
    private String mainImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "myReviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MyReviewsContents> myReviewsContents = new ArrayList<>();

    public static MyReviews toEntity(MyReviewsSaveRequestDto requestDto, String mainImageUrlFromS3 , Users users) {
        return MyReviews.builder()
                .exhibitionsTitle(requestDto.name())
                .exhibitionsLocation(requestDto.gallery())
                .visitedDate(requestDto.date())
                .grade(requestDto.rating())
                .mainImageUrl(mainImageUrlFromS3)
                .users(users)
                .myReviewsContents(new ArrayList<>())
                .build();
    }

    public void addContents(MyReviewsContents myReviewsContents) {
        this.myReviewsContents.add(myReviewsContents);
    }

    public void updateMyReviews(MyReviewsModifyRequestDto requestDto, String mainImageUrl) {
        this.exhibitionsTitle = requestDto.name();
        this.exhibitionsLocation = requestDto.gallery();
        this.visitedDate = requestDto.date();
        this.grade = requestDto.rating();
        this.mainImageUrl = mainImageUrl;
    }

}
