package com.fourttttty.corookie.project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProjectUpdateRequest(@NotBlank Long id,
                                   @NotBlank String name,
                                   @NotBlank String description,
                                   @NotBlank String invLink,
                                   @NotBlank Boolean invStatus) {
}
