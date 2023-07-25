package com.fourttttty.corookie.issue.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fourttttty.corookie.global.exception.InvalidIssuePriorityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IssuePriority {
    MUST("must"),
    SHOULD("should"),
    MIDDLE("middle"),
    MAY("may"),
    MIGHT("might");

    private final String value;

    @JsonCreator
    public static IssuePriority from(String value) {
        for (IssuePriority issuePriority : IssuePriority.values()) {
            if (issuePriority.getValue().equals(value)) {
                return issuePriority;
            }
        }
        throw new InvalidIssuePriorityException();
    }
}
