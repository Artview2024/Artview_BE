package com.backend.Artview.domain.communication.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String comment;
}
