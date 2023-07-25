package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.issue.application.repository.IssueCategoryRepository;
import com.fourttttty.corookie.issue.dto.response.IssueCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueCategoryService {
    private final IssueCategoryRepository issueCategoryRepository;

    public List<IssueCategoryResponse> findByIssueId(Long issueId) {
        return issueCategoryRepository.findByIssueId(issueId).stream()
                .map(issueCategory -> new IssueCategoryResponse(issueCategory.getCategory()))
                .toList();
    }
}
