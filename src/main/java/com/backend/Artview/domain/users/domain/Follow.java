package com.backend.Artview.domain.users.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Follow extends BaseEntity {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @ManyToOne
    @JoinColumn(name = "give_follow_id")
    private Users  giveFollowUsers; //팔로우 신청을 한 사람

    @ManyToOne
    @JoinColumn(name = "take_follow_id")
    private Users takeFollowUsers; //팔로우 신청을 당한 사람

    public static Follow toEntity(Users giveFollowUser, Users takeFollowUser) {
        return Follow.builder()
                .giveFollowUsers(giveFollowUser)
                .takeFollowUsers(takeFollowUser)
                .build();
    }
}
