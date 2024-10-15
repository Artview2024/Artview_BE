package com.backend.Artview.domain.communication.domain;

import com.backend.Artview.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CommunicationsImages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class CommunicationImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communications_images_id")
    private Long id;

    @Column(name = "communications_images_url", length = 500)
    private String imageUrl;

    @Column(name = "communications_images_title")
    private String imageTitle;

    @ManyToOne
    @JoinColumn(name = "communications_id")
    private Communications communications;

    public static CommunicationImages toEntity(String image,String imageTitle,Communications communications) {
        return CommunicationImages.builder()
                .imageUrl(image)
                .imageTitle(imageTitle)
                .communications(communications)
                .build();
    }

    public void addCommunications(Communications communications) {
        this.communications=communications;
    }
}
