package com.chenjiang.endurance.common;

public enum BasicErrorCode {
    USER_NOT_FIND("无法找到用户", "base.error.user_not_find"),

    LOGIN_FAILED("登录失败", "base.error.user_login_failed"),

    WRONG_PASSWORD("密码错误", "base.error.wrong_password"),

    DEFAULT("未知错误", "base.error.unknow_error"),

    UNAUTHORIZED("权限错误", "base.error.unauthorized"),

    ABSTRACT_TOO_LONG("文章摘要超过长度限制", "base.error.abstract_too_long"),

    ARTICLE_SOURCE_NOT_FOUND("无法找到记录", "base.error.article_source_not_find")

    ;


    BasicErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private final String message;

    private final String code;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
