package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.global.exception.IssueNotFoundException;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueCategoryResponse;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
import com.fourttttty.corookie.issue.dto.response.IssueListResponse;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.project.application.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueCategoryService issueCategoryService;
    private final ProjectService projectService;
    private final MemberService memberService;

    @Transactional
    public IssueDetailResponse create(IssueCreateRequest issueCreateRequest, Long projectId, Long memberId) {
        Issue issue = issueRepository.save(issueCreateRequest.toEntity(
                projectService.findEntityById(projectId),
                memberService.findEntityById(memberId)));
        List<IssueCategoryResponse> issueCategoryResponses = issueCreateRequest.issueCategories().stream()
                .map(request -> issueCategoryService.createIfNone(request, issue))
                .toList();
        return IssueDetailResponse.from(issue, issueCategoryResponses, memberId);
    }

    public IssueDetailResponse findById(Long issueId) {
        Issue issue = findEntityById(issueId);
        return IssueDetailResponse.from(issue, issueCategoryService.findByIssueId(issueId), issue.getManager().getId());
    }

    public Issue findEntityById(Long issueId) {
        return issueRepository.findById(issueId).orElseThrow(IssueNotFoundException::new);
    }

    public List<IssueListResponse> findByProjectId(Long projectId, Long memberId) {
        return issueRepository.findByProjectId(projectId).stream()
                .map(issue -> IssueListResponse
                        .from(issue, issueCategoryService.findByIssueId(issue.getId()), projectId, memberId))
                .toList();
    }

    @Transactional
    public void deleteById(Long issueId) {
        findEntityById(issueId).delete();
    }
}
