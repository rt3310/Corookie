package com.fourttttty.corookie.global.exception;

import lombok.Getter;

@Getter
public class ProjectNotEnabledException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public ProjectNotEnabledException() {
        this(ExceptionCode.PROJECT_NOT_ENABLED);
    }

    public ProjectNotEnabledException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
