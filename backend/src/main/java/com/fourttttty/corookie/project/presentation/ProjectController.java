package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> projects(){
        return null;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> projectDetail(@PathVariable Long projectId) {
        return ResponseEntity.ok(new ProjectResponse(projectService.findById(projectId).getName()));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> postProjectDetail(@PathVariable Long projectId) {

        return ResponseEntity.ok(new ProjectResponse(projectService.findById(projectId).getName()));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> putProjectDetail(@PathVariable Project project) {
        //Project projectDetail =
        projectService.update(project);
        return null;
        //return ResponseEntity.ok(new ProjectResponse());
    }


    @DeleteMapping
    public ResponseEntity<ProjectResponse> deleteProjectDetail(@PathVariable Long projectId) {
        return ResponseEntity.ok(new ProjectResponse(projectService.findById(projectId).getName()));
    }




    /*
    PUT	/api/v1/projects/{projectId}
    DELETE	/api/v1/projects/{projectId}
*/
}
