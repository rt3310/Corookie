package com.fourttttty.corookie.issue.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fourttttty.corookie.global.exception.InvalidIssueCategoryException;
import com.fourttttty.corookie.global.exception.InvalidIssuePriorityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Category {
    ALL("all"),
    BACKEND("backend"),
    FRONTEND("frontend"),
    DESIGN("design"),
    PLAN("plan"),
    ETC("etc");

    private final String value;

    @JsonCreator
    public static Category from(String value) {
        for (Category category : Category.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        throw new InvalidIssueCategoryException();
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
