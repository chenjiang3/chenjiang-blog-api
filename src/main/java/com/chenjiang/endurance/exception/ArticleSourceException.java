package com.chenjiang.endurance.exception;

import com.chenjiang.endurance.common.BaseException;
import com.chenjiang.endurance.common.BasicErrorCode;

public class ArticleSourceException extends BaseException {

    public ArticleSourceException(BasicErrorCode errorCode) {
        super(errorCode);
    }

}
