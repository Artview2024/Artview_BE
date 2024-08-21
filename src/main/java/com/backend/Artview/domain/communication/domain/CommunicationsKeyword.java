package com.backend.Artview.domain.communication.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "communicationsKeyword")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommunicationsKeyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communications_keyword_id")
    private Long id;

    private String keyword;

    @ManyToOne
    @JoinColumn(name = "communications_id")
    private Communications communications;

    public static CommunicationsKeyword toEntity(String keyword, Communications communications) {
        return CommunicationsKeyword.builder()
                .keyword(keyword)
                .communications(communications)
                .build();
    }
}
