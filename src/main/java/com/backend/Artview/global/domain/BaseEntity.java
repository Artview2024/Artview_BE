package com.backend.Artview.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //이 클래스 자첸은 엔티티가 아니며 데이터베이스 테이블로 매핑되지 않음. 하지만 이 클래스를 상속받은 클래스들은 부모 클래스의 필드와 매핑 설정을 그대로 사용할 수 있음
@EntityListeners(AuditingEntityListener.class) //해당 엔티티가 생성되거나 수정될 때 자동으로 특정 작업을 수행하도록 함
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @CreatedDate
    @CreationTimestamp //insert할 떄 시간 자동으로 들어== DEFAULT CURRENT_TIMESTAMP
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @UpdateTimestamp //update문 돌아갈 때 자동으로 시간 저장
    private LocalDateTime updateDate;
}
