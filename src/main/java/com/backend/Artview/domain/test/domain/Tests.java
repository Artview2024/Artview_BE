package com.backend.Artview.domain.test.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Tests {

    @Id
    private Long id;

    private String name;
}
