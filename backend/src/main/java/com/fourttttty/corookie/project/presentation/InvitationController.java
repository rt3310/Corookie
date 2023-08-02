package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.global.exception.ProjectNotEnabledException;
import com.fourttttty.corookie.global.exception.ProjectNotOpenForInvitationException;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.service.ProjectMemberService;
import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{invitationLink}")
public class InvitationController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<ProjectResponse> enterProject(@PathVariable String invitationLink){
        /*
        To-Do :
        - 로그인되지 않으면 로그인 페이지로 보내기
        - 로그인되어있는 멤버 정보 얻어오기
        */
        Member member = Member.of("이름", "test@test.com", null);
        //실제 멤버가 아닌 가상의 멤버 정보임, 실제 로그인된 멤버 정보로 대체할 것.

        // 프로젝트
        Project project = projectService.findEntityByInvitationLink(invitationLink);
        if(!project.getEnabled()){
            throw new ProjectNotEnabledException();
        }else if(!project.getInvitationStatus()){
            throw new ProjectNotOpenForInvitationException();
        }
        projectMemberService.create(new ProjectMemberCreateRequest(project.getId(), member.getId()));
        return ResponseEntity.ok().build();
    }
}
