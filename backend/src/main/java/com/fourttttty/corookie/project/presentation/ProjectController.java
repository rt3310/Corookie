package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
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
        return ResponseEntity.ok(projectService.findAll().stream().toList());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> projectDetail(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.findById(projectId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> projectCreate(@RequestBody @Validated ProjectCreateRequest request) {
        ProjectResponse projectResponse = projectService.create(Project.builder()
                .name(request.name())
                .description(request.description())
                .invLink(request.invLink())
                .build());
        //TO-DO : textChannelService로 새 채널 생성 시 프로젝트 정보까지 저장할 것.
        return ResponseEntity.ok(projectResponse);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> projectModifyName(@RequestBody ProjectUpdateRequest request){
        ProjectResponse projectResponse = projectService.modify(request);
        return ResponseEntity.ok(projectResponse);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Object> projectDelete(@PathVariable Long projectId) {
        return ResponseEntity.noContent().build();
    }

    public void addMemberToProject(Long id){
        projectService.findById(id);
    }

    public void removeMemberFromProject(){

    }
}
