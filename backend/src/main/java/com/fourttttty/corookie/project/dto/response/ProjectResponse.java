package com.fourttttty.corookie.project.dto.response;

import com.fourttttty.corookie.project.domain.Project;
import lombok.Builder;
import java.time.LocalDateTime;

public record ProjectResponse(String name,
                              String description,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              Boolean enabled,
                              String invLink,
                              Boolean invStatus) {

    @Builder
    public ProjectResponse {
    }


    public static ProjectResponse of(Project project) {
          return ProjectResponse.builder()
                  .name(project.getName())
                  .createdAt(project.getCreatedAt())
                  .updatedAt(project.getUpdatedAt())
                  .enabled(project.isEnabled())
                  .invLink(project.getInvLink())
                  .invStatus(project.isInvStatus())
                  .build();
         }

}
