package com.backend.Artview.domain.users.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Follow extends BaseEntity {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private Users follower; //팔로우를 한 사람

    @ManyToOne
    @JoinColumn(name = "followee")
    private Users followee; //팔로우를 당한 사람

    public static Follow toEntity(Users follower, Users followee) {
        return Follow.builder()
                .follower(follower)
                .followee(followee)
                .build();
    }
}
