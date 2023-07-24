package com.fourttttty.corookie.plan.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotNull String categoryContent) {
}
