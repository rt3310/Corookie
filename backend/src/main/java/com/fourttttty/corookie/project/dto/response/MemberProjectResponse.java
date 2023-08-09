package com.fourttttty.corookie.project.dto.response;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberProjectResponse(String name,
                                    String description,
                                    LocalDateTime createdAt,
                                    LocalDateTime updatedAt,
                                    Boolean enabled,
                                    String invitationLink,
                                    Boolean invitationStatus) {

    public static MemberProjectResponse from(ProjectMember projectMember) {
        Project project = projectMember.getId().getProject();
        return MemberProjectResponse.builder()
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .enabled(project.getEnabled())
                .invitationLink(project.getInvitationLink())
                .invitationStatus(project.getInvitationStatus())
                .build();
    }
}
