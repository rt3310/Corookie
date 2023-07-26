package com.fourttttty.corookie.comment.application.repository;

import com.fourttttty.corookie.comment.domain.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAll(Long threadId);
}
