package com.fourttttty.corookie.comment.infrastructure;

import com.fourttttty.corookie.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByThreadId(Long threadId);
}
