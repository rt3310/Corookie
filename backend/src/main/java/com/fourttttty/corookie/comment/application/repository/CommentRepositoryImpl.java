package com.fourttttty.corookie.comment.application.repository;

import com.fourttttty.corookie.comment.domain.Comment;
import com.fourttttty.corookie.comment.infrastructure.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> findAll(Long threadId) {
        return commentJpaRepository.findAllById();
    }
}
