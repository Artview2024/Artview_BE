package com.backend.Artview.domain.communication.domain;

import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "communications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Communications extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communications_id")
    private Long id;

    private String name; //전시회명

    private String rate; //별점

    private String date;  //방문날짜

    private String gallery; //장소명

    private String content; //글쓰기 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "communications", cascade = CascadeType.ALL)
    private List<CommunicationsKeyword> communicationsKeywordsList;

    @OneToMany(mappedBy = "communications", cascade = CascadeType.ALL)
    private List<CommunicationImages> communicationImagesList;


    public static Communications toEntity(CommunicationSaveRequestDto dto, Users users) {
        return Communications.builder()
                .name(dto.name())
                .rate(dto.rate())
                .date(dto.date())
                .gallery(dto.gallery())
                .content(dto.content())
                .users(users)
                .build();
    }

    public void addCommunicationImages(List<CommunicationImages> communicationImagesList) {
        this.communicationImagesList = communicationImagesList;
    }

    public void addCommunicationsKeyword(List<CommunicationsKeyword> communicationsKeywordList) {
        this.communicationsKeywordsList = communicationsKeywordList;
    }
}
