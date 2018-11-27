package com.chenjiang.endurance.common;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiDecorator implements ApiListener {

    @Override
    public void onRequest(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("onRequest");
    }

    @Override
    public void onResponse(HttpServletResponse response) {
//        System.out.println("onResponse");
        response.setHeader("X-TIMESTAMP", String.valueOf(TimeUtil.currentTS()));
    }

    @Override
    public void onComplete() {
    }

}
