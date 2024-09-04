package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
