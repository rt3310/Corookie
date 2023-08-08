package com.fourttttty.corookie.project.dto.response;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import lombok.Builder;

@Builder
public record ProjectMemberResponse(Long id,
                                    String name,
                                    String email) {
    public static ProjectMemberResponse from(ProjectMember projectMember) {
        Member member = projectMember.getId().getMember();
        return new ProjectMemberResponse(member.getId(), member.getName(), member.getEmail());
    }
}
