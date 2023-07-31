package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.config.audit.JpaAuditingConfig;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.application.service.ProjectMemberService;
import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.support.RestDocsTest;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProjectController.class)
public class ProjectMemberControllerTest extends RestDocsTest {
    MemberRepository memberRepository;
    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectMemberService projectMemberService;

    @MockBean
    private ProjectService projectService;

    private Member member1;
    private Member member2;
    private Project project;

    private ProjectMember projectMember1;
    private ProjectMember projectMember2;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void initTexture() {
        memberRepository = new FakeMemberRepository();
        projectRepository = new FakeProjectRepository();
        projectMemberRepository = new FakeProjectMemberRepository();

        member1 = new Member("member1");
        member2 = new Member("member2");

        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                null
        );

        projectMember1 = ProjectMember.of(project, member1);
        projectMember2 = ProjectMember.of(project, member2);
    }

    @Test
    @DisplayName("프로젝트 멤버 제거")
    void deleteProjectMember() throws Exception {
        project = mock(Project.class);
        member1 = mock(Member.class);
        member2 = mock(Member.class);

        projectRepository.save(project);
        memberRepository.save(member1);
        memberRepository.save(member2);

        // given
        given(project.getId()).willReturn(1L);
        given(member1.getId()).willReturn(1L);
        given(member2.getId()).willReturn(2L);

        //1번째 delete를 했을 때 projectMember만 사라지고
        // when
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/1/members?memberId=1")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        Project projectAfter1stDeletion = projectRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        //System.out.println(projectMemberRepository.findByProject(projectAfterDeletion).toString());

        //projectMemberService.deleteProjectMember(1L, 1L);
        //projectMemberService.deleteProjectMember(1L, 2L);

        //2번째 delete를 했을 때 projectMember도 project도 사라진다. (get을 한 결과가 없거나 enabled가 false)

    }

}
