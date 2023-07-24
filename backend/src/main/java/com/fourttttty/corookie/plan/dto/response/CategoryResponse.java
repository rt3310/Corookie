package com.fourttttty.corookie.plan.dto.response;

import com.fourttttty.corookie.plan.domain.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryResponse(@NotNull Long CategoryId,
                              @NotNull String CategoryContent){

  public static CategoryResponse of(Category category){
    return CategoryResponse.builder()
        .CategoryId(category.getId())
        .CategoryContent(category.getCategoryContent())
        .build();
  }

}
