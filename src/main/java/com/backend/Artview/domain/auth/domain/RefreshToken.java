package com.backend.Artview.domain.auth.domain;

import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refreshTokenId", unique = true)
    private Long id;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public static RefreshToken toEntity(String userRefreshToken,Users users) {
        return RefreshToken.builder()
                .refreshToken(userRefreshToken)
                .users(users)
                .build();
    }
}
