package com.chenjiang.endurance.exception;

import com.chenjiang.endurance.common.BaseException;
import com.chenjiang.endurance.common.BasicErrorCode;

public class ArticleException extends BaseException {
    public ArticleException(BasicErrorCode errorCode) {
        super(errorCode);
    }
}
