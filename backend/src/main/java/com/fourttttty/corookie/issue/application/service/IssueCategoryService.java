package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.issue.application.repository.IssueCategoryRepository;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.dto.request.IssueCategoryCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueCategoryService {
    private final IssueCategoryRepository issueCategoryRepository;

    public IssueCategoryResponse createIfNone(IssueCategoryCreateRequest issueCategoryCreateRequest, Issue issue) {
        return issueCategoryRepository.findByCategory(issueCategoryCreateRequest.category())
                .map(issueCategory -> IssueCategoryResponse.from(issueCategory.getCategory()))
                .orElseGet(() -> IssueCategoryResponse.from(issueCategoryRepository
                        .save(issueCategoryCreateRequest.toEntity(issue)).getCategory()));
    }

    public List<IssueCategoryResponse> findByIssue(Issue issue) {
        return issueCategoryRepository.findByIssue(issue).stream()
                .map(issueCategory -> IssueCategoryResponse.from(issueCategory.getCategory()))
                .toList();
    }
}
