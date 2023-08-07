package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.junit.jupiter.api.BeforeEach;

public class ProjectMemberRepositoryTest {

    ProjectMemberRepository projectMemberRepository;
    ProjectMember projectMember;
    ProjectRepository projectRepository;
    MemberRepository memberRepository;

    @BeforeEach
    void initObjects(){
        projectRepository = new FakeProjectRepository();
        projectMemberRepository = new FakeProjectMemberRepository(projectRepository, memberRepository);
    }
}
