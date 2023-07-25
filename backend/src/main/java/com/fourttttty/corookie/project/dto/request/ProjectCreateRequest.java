package com.fourttttty.corookie.project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProjectCreateRequest(@NotBlank String name,
                                   @NotBlank String description,
                                   String invLink,
                                   Boolean invStatus) {
}
