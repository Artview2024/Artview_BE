package com.backend.Artview.domain.Communication.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Communications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Communication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_communications_id")
    private Long id;

    private String name; //전시회명

    private String rate; //별점

    private String date;  //방문날짜

    private String gallery; //장소명

    private String content; //글쓰기 내용

    private String keyword; //감상 키워드
}
