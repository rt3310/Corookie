package com.fourttttty.corookie.comment.application.service;

import com.fourttttty.corookie.comment.application.repository.CommentRepository;
import com.fourttttty.corookie.comment.domain.Comment;
import com.fourttttty.corookie.comment.dto.request.CommentCreateRequest;
import com.fourttttty.corookie.comment.dto.request.CommentModifyRequest;
import com.fourttttty.corookie.comment.dto.response.CommentDetailResponse;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.thread.application.service.ThreadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final ThreadService threadService;
    private final MemberService memberService;

    public List<CommentDetailResponse> findAll(Long threadId) {
        return commentRepository.findAll(threadId)
                .stream()
                .map(CommentDetailResponse::of)
                .toList();
    }

    @Transactional
    public CommentDetailResponse create(CommentCreateRequest request, Long writerId) {
        Comment comment = Comment.create(
                request.content(),
                threadService.findEntityById(request.threadId()),
                memberService.findEntityById(writerId),
                true);
        return CommentDetailResponse.of(commentRepository.save(comment));
    }

    @Transactional
    public CommentDetailResponse modify(CommentModifyRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);
        comment.modify(request.content());
        return CommentDetailResponse.of(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);
        comment.delete();
    }

}
