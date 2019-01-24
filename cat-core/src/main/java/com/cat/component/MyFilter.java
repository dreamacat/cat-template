package com.cat.component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxiaoqiang
 * Filte是Servlet规范规定的，只能用于web程序中，是servlet容器支持的，作用范围是servlet前后；
 * @since 2018/10/23
 **/
//@Order(1)
//@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().contains("favicon.ico")) {
            System.out.println("MyFilter doFilter return");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println(req.getRequestURL());
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy");

    }
}
