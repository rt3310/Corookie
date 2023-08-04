package com.fourttttty.corookie.project.presentation;


import com.fourttttty.corookie.project.application.service.ProjectMemberService;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/projectmembers")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<Object> projectMemberCreate(ProjectMemberCreateRequest request) {
        projectMemberService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> projectMemberDelete(@PathVariable Long memberId,
                                                      @PathVariable Long projectId) {
        projectMemberService.deleteProjectMember(memberId, projectId);
        return ResponseEntity.noContent().build();
    }
}
