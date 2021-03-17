package com.seyongkim.shortening_url_demo.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    CONSTRAINT_VIOLATION(400, "C002", " Input Value should not null or blank"),
    NOT_FOUND(404, "C003", " Not Found"),
    METHOD_NOT_ALLOWED(405, "C004", " Invalid HTTP Method"),
    INTERNAL_SERVER_ERROR(500, "C005", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
