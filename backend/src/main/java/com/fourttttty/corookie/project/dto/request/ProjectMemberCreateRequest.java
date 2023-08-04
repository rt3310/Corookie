package com.fourttttty.corookie.project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectMemberCreateRequest(@NotNull Long projectId,
                                         @NotNull Long memberId)  {
}
