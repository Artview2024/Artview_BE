package com.backend.Artview.domain.kimyh;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany
    private List<Orders> orders;
}
