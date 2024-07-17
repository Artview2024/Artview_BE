package com.backend.Artview.domain.users.domain;

import com.backend.Artview.domain.MyReviews.domain.VisitedExhibitions;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
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

    @OneToMany(mappedBy = "users")
    private List<VisitedExhibitions> visitedExhibitions = new ArrayList<>();

}
