package com.fourttttty.corookie.project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProjectUpdateRequest(@NotBlank String name) {
}
