package com.chenjiang.endurance.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ApiListener {

    default void onRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    default void onResponse(HttpServletResponse response) {
    }

    default void onComplete() {
    }

}
