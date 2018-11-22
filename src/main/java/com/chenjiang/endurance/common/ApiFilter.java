package com.chenjiang.endurance.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class ApiFilter implements Filter {

    @Autowired
    private ApiListener[] listeners;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Arrays.asList(listeners).stream().forEach(listener -> {
                try {
                    listener.onRequest((HttpServletRequest)request, (HttpServletResponse)response);
                } catch (Exception ex) {
                    // MARK: TODO
                }
            });
            chain.doFilter(request, response);

            Arrays.asList(listeners).stream().forEach(listener -> {
                try {
                    listener.onResponse((HttpServletResponse)response);
                } catch (Exception ex) {
                    // MARK: TODO
                }
            });
        } finally {

        }
    }

    @Override
    public void destroy() {
    }
}
