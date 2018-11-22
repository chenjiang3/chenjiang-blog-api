package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.common.BaseException;
import com.chenjiang.endurance.common.BasicErrorCode;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.jws.WebResult;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    private final ResponseEntityExceptionHandler handler = new ResponseEntityExceptionHandlerImpl();
    private final ErrorAttributes errorAttributes;

    GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleException(WebRequest request, HttpServletRequest httpServletRequest, BaseException ex) throws Exception {
        ResponseEntity<Object> response = null;
        try {
            response = handler.handleException(ex, request);
        } catch (Exception e) {
            // MARK: TODO
        }
        HttpStatus status = getStatus(response, ex);
        httpServletRequest.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, status.value());
        httpServletRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex);
        String message = ex.getMessage();
        httpServletRequest.setAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE, message);
        Map<String, Object> responses = errorAttributes.getErrorAttributes(request, false);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if (response != null) {
            headers.addAll(response.getHeaders());
        }
        return new ResponseEntity<>(responses, headers, status);
    }

    public static HttpStatus getStatus(ResponseEntity<Object> response, BaseException ex) {
        switch (ex.getErrorCode()) {
            case USER_NOT_FIND:
                return HttpStatus.BAD_REQUEST;
            case DEFAULT:
                return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.BAD_REQUEST;
    }


    private class ResponseEntityExceptionHandlerImpl extends ResponseEntityExceptionHandler {
    }

}





























