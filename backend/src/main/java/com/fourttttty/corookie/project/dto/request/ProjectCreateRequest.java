package com.fourttttty.corookie.project.dto.request;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.nio.ByteBuffer;
import java.util.UUID;

public record ProjectCreateRequest(@NotBlank String name,
                                   @NotNull String description,
                                   @NotNull Boolean invitationStatus) {

    public Project toEntity(String invitationLink, Member member) {
        return Project.of(name, description, true, invitationLink, invitationStatus, member);
    }
}
