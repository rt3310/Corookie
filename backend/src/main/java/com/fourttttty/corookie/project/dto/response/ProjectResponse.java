package com.fourttttty.corookie.project.dto.response;

import com.fourttttty.corookie.project.domain.Project;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ProjectResponse(Long id,
                              String name,
                              String description,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              Boolean enabled,
                              String invLink,
                              Boolean invStatus){

 public static ProjectResponse of(Project project, Boolean enabled, Boolean invStatus){
  return ProjectResponse.builder()
          .id(project.getId())
          .name(project.getName())
          .createdAt(project.getCreatedAt())
          .updatedAt(project.getUpdatedAt())
          .enabled(enabled)
          .invLink(project.getInvLink())
          .invStatus(invStatus)
          .build();
 }

}
