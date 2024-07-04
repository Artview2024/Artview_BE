package com.backend.Artview.domain.review.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reviews extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_reviwes_id", unique = true)
    private Long id;

    @Column(name = "art_title", nullable = false)
    private String artTitle;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "note", nullable = false)
    private String note;

}
