package com.fourttttty.corookie.comment.application.repository;

import com.fourttttty.corookie.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findAll(Long threadId);

    Optional<Comment> findById(Long commentId);

    Comment save(Comment comment);


}
