package com.chenjiang.endurance.common;

public class BaseException extends RuntimeException {

    private BasicErrorCode errorCode;

    public BaseException(BasicErrorCode code) {
        super(code.getMessage());
        this.errorCode = code;
    }

    public BasicErrorCode getErrorCode() {
        return errorCode;
    }
}
