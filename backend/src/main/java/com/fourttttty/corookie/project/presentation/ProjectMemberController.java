package com.fourttttty.corookie.project.presentation;


import com.fourttttty.corookie.project.application.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> projectMemberDelete(@PathVariable Long memberId, @PathVariable Long projectId) {
        projectMemberService.deleteProjectMember(memberId, projectId);
        return ResponseEntity.noContent().build();
    }
}
