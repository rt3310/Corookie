package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.global.exception.IssueNotFoundException;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
import com.fourttttty.corookie.issue.dto.response.IssueListResponse;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueCategoryService issueCategoryService;
    private final MemberService memberService;

    public IssueDetailResponse create(IssueCreateRequest issueCreateRequest, Long projectId, Long memberId) {
        Issue issue = issueRepository.save(issueCreateRequest.toEntity(memberService.findById(memberId)));
        return IssueDetailResponse.of(issue, issueCategoryService.findByIssueId(issue.getId()), projectId, memberId);
    }

    public IssueDetailResponse findById(Long issueId, Long projectId, Long memberId) {
        return IssueDetailResponse.of(
                issueRepository.findById(issueId).orElseThrow(IssueNotFoundException::new),
                issueCategoryService.findByIssueId(issueId), projectId, memberId);
    }

    public List<IssueListResponse> findByProjectId(Long projectId, Long memberId) {
        return issueRepository.findByProjectId(projectId).stream()
                .map(issue -> IssueListResponse
                        .of(issue, issueCategoryService.findByIssueId(issue.getId()), projectId, memberId))
                .toList();
    }
}
