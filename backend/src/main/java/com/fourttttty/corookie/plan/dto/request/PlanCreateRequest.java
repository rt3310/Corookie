package com.fourttttty.corookie.plan.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlanCreateRequest(@NotNull String planName,
                                @NotNull String description,
                                @NotNull LocalDateTime planStart,
                                @NotNull LocalDateTime planEnd,
                                @NotNull List<PlanCategoryCreateRequest> categories) {
}
