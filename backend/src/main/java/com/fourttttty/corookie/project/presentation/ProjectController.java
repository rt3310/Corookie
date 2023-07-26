package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> projectList() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> projectDetail(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.findById(projectId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> projectCreate(@RequestBody @Validated ProjectCreateRequest projectCreateRequest,
                                                         @RequestParam Long memberId) {
        return ResponseEntity.ok(projectService.create(projectCreateRequest, memberId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> projectModify(@PathVariable Long projectId,
                                                         @RequestBody @Validated ProjectUpdateRequest projectUpdateRequest){
        return ResponseEntity.ok(projectService.modify(projectUpdateRequest, projectId));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Object> projectDelete(@PathVariable Long projectId) {
        projectService.deleteById(projectId);
        return ResponseEntity.noContent().build();
    }
}
