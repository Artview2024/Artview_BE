package com.backend.Artview.domain.kimyh;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;

    private LocalDateTime orderDate;
    private OrderStatus status;

    @ManyToOne
    Member member;
}
