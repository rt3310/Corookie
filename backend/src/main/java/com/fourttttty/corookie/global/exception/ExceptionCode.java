package com.fourttttty.corookie.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Getter
public enum ExceptionCode {

    ISSUE_NOT_FOUND(BAD_REQUEST, "ISSUE_001", "이슈를 찾을 수 없습니다"),
    ISSUE_PROGRESS_INVALID(BAD_REQUEST, "ISSUE_002", "유효하지 않은 이슈 진행입니다."),
    ISSUE_PRIORITY_INVALID(BAD_REQUEST, "ISSUE_003", "유효하지 않은 이슈 중요도입니다."),
    ISSUE_CATEGORY_INVALID(BAD_REQUEST, "ISSUE_004", "유효하지 않은 이슈 분류입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
