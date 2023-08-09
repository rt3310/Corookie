package com.fourttttty.corookie.project.dto.response;

import com.fourttttty.corookie.project.domain.Project;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectListResponse(Long id,
                                  String name,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt,
                                  Boolean enabled) {
    public static ProjectListResponse from(Project project) {
        return ProjectListResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .enabled(project.getEnabled())
                .build();
    }
}
