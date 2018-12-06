package com.chenjiang.endurance.interceptor;

import com.chenjiang.endurance.common.BaseException;
import com.chenjiang.endurance.common.BasicErrorCode;
import com.chenjiang.endurance.service.AuthorizationService;
import com.chenjiang.endurance.service.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            CheckToken access = method.getAnnotation(CheckToken.class);
            if (access != null) {
                String authorization = request.getHeader("Authorization");
                if (StringUtils.isEmpty(authorization)) {
                    throw new BaseException(BasicErrorCode.UNAUTHORIZED);
                }
                if (authorizationService.checkToken(authorization)) {
                    return true;
                }
                throw new BaseException(BasicErrorCode.UNAUTHORIZED);
            }
            return true;
        }
        return true;
    }

}
