package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.global.exception.IssueNotFoundException;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
import com.fourttttty.corookie.issue.dto.response.IssueListResponse;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    private final IssueCategoryService issueCategoryService;

    @Transactional
    public IssueDetailResponse create(IssueCreateRequest issueCreateRequest, Long projectId, Long memberId) {
        Issue issue = issueRepository.save(issueCreateRequest.toEntity(
                projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new),
                memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new)));

        return IssueDetailResponse.from(issue,
                issueCreateRequest.issueCategories().stream()
                        .map(request -> issueCategoryService.createIfNone(request, issue))
                        .toList());
    }

    public IssueDetailResponse findById(Long issueId) {
        Issue issue = findEntityById(issueId);
        return IssueDetailResponse.from(issue, issueCategoryService.findByIssue(issue));
    }

    public List<IssueListResponse> findByProjectId(Long projectId) {
        return issueRepository.findByProjectId(projectId).stream()
                .map(issue -> IssueListResponse.from(issue, issueCategoryService.findByIssue(issue)))
                .toList();
    }

    @Transactional
    public void deleteById(Long issueId) {
        findEntityById(issueId).delete();
    }

    private Issue findEntityById(Long issueId) {
        return issueRepository.findById(issueId).orElseThrow(IssueNotFoundException::new);
    }
}
