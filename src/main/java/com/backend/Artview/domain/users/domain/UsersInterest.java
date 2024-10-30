package com.backend.Artview.domain.users.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users_interest")
@Getter
public class UsersInterest extends BaseEntity {

    @Id
    @Column(name = "users_interest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "users_interest_content")
    private String usersInterestContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    public static UsersInterest of(String usersNewInterest, Users users) {
        return UsersInterest.builder()
                .usersInterestContent(usersNewInterest)
                .users(users)
                .build();
    }
}
