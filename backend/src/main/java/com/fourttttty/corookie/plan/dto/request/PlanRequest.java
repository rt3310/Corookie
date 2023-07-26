package com.fourttttty.corookie.plan.dto.request;

import jakarta.validation.constraints.NotNull;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlanRequest(@NotNull String planName,
                          @NotNull String description,
                          @NotNull LocalDateTime planStart,
                          @NotNull LocalDateTime planEnd) {
}
