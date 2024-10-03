package com.backend.Artview.domain.users.domain;

import com.backend.Artview.domain.auth.domain.RefreshToken;
import com.backend.Artview.domain.auth.dto.response.KakaoUserInfoResponseDto;
import com.backend.Artview.domain.communication.domain.Comment;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.communication.domain.Like;
import com.backend.Artview.domain.communication.domain.Scrap;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Users extends BaseEntity {

    @Id
    @Column(name = "users_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

//    @Column(name = "nick_name")
//    private String nickName;

//    @Column(name = "password")
//    private String password;

    @Column(name = "user_image")
    private String userImage;

    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<MyReviews> myReviews = new ArrayList<>();

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Communications> communicationsList = new ArrayList<>();

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<Scrap> scrapList = new ArrayList<>();

    @OneToOne(mappedBy = "users")
    private RefreshToken refreshToken;


//    @OneToMany(mappedBy = "followee",fetch = FetchType.LAZY)
//    private List<Follow> followee = new ArrayList<>();
//
//    @OneToMany(mappedBy = "follower",fetch = FetchType.LAZY)
//    private List<Follow> follower = new ArrayList<>();

    public static Users toEntity(KakaoUserInfoResponseDto kakaoUserInfo) {
        return Users.builder()
                .kakaoId(kakaoUserInfo.getId())
                .email(kakaoUserInfo.getKakao_account().getEmail())
                .name(kakaoUserInfo.getKakao_account().getProfile().getNickname())
                .userImage(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
                .build();
    }
}
