package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.support.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest extends RestDocsTest {
    @MockBean
    private ProjectService projectService;

    private Member member;
    private Project project;

    @BeforeEach
    void initTexture(){
        member = new Member("member");
        project = Project.of("project",
                "description",
                true,
                "http://test.com",
                false,
                member);
    }

    //To-Do : createProject
    
}
