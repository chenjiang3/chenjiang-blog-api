package com.chenjiang.endurance.exception;

import com.chenjiang.endurance.common.BaseException;
import com.chenjiang.endurance.common.BasicErrorCode;
import org.springframework.http.HttpStatus;

public class UserException extends BaseException {

    public UserException(BasicErrorCode errorCode) {
        super(errorCode);
    }

}
