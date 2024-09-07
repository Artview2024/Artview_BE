package com.backend.Artview.domain.users.domain;

import com.backend.Artview.domain.communication.domain.Comment;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.communication.domain.Like;
import com.backend.Artview.domain.communication.domain.Scrap;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {

    @Id
    @Column(name = "users_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "password")
    private String password;

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
}
