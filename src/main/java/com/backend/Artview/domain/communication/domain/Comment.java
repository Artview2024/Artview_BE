package com.backend.Artview.domain.communication.domain;

import com.backend.Artview.domain.communication.dto.request.CommunicationsCommentRequestDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;

@Entity
@Table(name = "communicationsComment")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content; //댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communications_id")
    private Communications communications;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentContent; //부모 댓글

    public static Comment toEntity(CommunicationsCommentRequestDto dto, Users users, Communications communications, Comment parentContent) {
        return Comment.builder()
                .content(dto.content())
                .users(users)
                .communications(communications)
                .parentContent(parentContent)
                .build();
    }
}
