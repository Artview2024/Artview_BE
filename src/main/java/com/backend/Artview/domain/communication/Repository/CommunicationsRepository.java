package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Communications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CommunicationsRepository extends JpaRepository<Communications,Long> {

    @Query(
            "SELECT co FROM Communications co WHERE co.id < :cursor ORDER BY co.createDate DESC"
    )
    Slice<Communications> findCommunicationsByCursorTopBy(@Param("cursor") Long cursor, PageRequest pageRequest);

    Slice<Communications> findCommunicationsTopBy(PageRequest pageRequest);

    List<Communications> findAllByUsersId(Long userId);
}
