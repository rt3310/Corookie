package com.fourttttty.corookie.project.dto.response;

import com.fourttttty.corookie.project.domain.Project;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ProjectResponse(String name,
                              String description,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              Boolean enabled,
                              String invitationLink,
                              Boolean invitationStatus) {

    public static ProjectResponse from(Project project) {
        return ProjectResponse.builder()
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
