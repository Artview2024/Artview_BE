package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(
            "SELECT c " +
            "FROM Comment c " +
            "JOIN Communications co ON co.id = c.communications.id " + 
            "JOIN Users u ON u.id = c.users.id " +//커뮤니티id와 user에 해당하는 댓글을 찾음
            "LEFT JOIN Comment pc ON pc.id = c.parentContent.id " +
            "WHERE c.communications.id = :communicationsId " +
            "ORDER BY c.parentContent.id asc, c.createDate asc"
    )
    List<Comment> findCommentByCommunicationsId(@Param("communicationsId") Long communicationsId);
}
