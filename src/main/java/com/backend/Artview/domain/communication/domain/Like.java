package com.backend.Artview.domain.communication.domain;

import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "communicationsLike")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communications_id")
    private Communications communications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    public static Like toEntity(Communications communications, Users users) {
        return Like.builder()
                .communications(communications)
                .users(users)
                .build();
    }
}
