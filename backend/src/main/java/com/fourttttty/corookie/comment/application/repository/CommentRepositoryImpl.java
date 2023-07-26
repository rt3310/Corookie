package com.fourttttty.corookie.comment.application.repository;

import com.fourttttty.corookie.comment.domain.Comment;
import com.fourttttty.corookie.comment.infrastructure.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> findAll(Long threadId) {
        return commentJpaRepository.findByThreadId(threadId);
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentJpaRepository.findById(commentId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }


}
