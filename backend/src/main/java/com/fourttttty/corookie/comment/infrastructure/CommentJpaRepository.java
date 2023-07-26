package com.fourttttty.corookie.comment.infrastructure;

import com.fourttttty.corookie.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
