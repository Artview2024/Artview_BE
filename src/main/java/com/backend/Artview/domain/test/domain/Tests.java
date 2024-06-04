package com.backend.Artview.domain.test.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tests")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tests extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tests_id", unique = true)
    public Long id;

    public String name;

}
