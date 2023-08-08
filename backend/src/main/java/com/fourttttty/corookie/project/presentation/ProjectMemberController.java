package com.fourttttty.corookie.project.presentation;


import com.fourttttty.corookie.project.application.service.ProjectMemberService;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/projectmembers")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<Object> projectMemberCreate(ProjectMemberCreateRequest request) {
        projectMemberService.createIfNone(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> projectMemberDelete(@PathVariable Long memberId,
                                                      @PathVariable Long projectId) {
        projectMemberService.delete(memberId, projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> projectMemberCount(@PathVariable Long projectId) {
        Long count = projectMemberService.countProjectMember(projectId);
        return ResponseEntity.ok(count);
    }

    @GetMapping
    public ResponseEntity<List<ProjectMemberResponse>> projectMemberList(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.findByProjectId(projectId));
    }
}
