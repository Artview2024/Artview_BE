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

    @Column(name = "communications_images_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "communications_id")
    private Communications communications;

    public static CommunicationImages toEntity(String image,Communications communications) {
        return CommunicationImages.builder()
                .imageUrl(image)
                .communications(communications)
                .build();
    }

    public void addCommunications(Communications communications) {
        this.communications=communications;
    }
}
