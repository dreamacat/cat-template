package com.cat.component;

import com.cat.annotations.AuthZ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author wangxiaoqiang
 * @since 2018/10/26
 **/
@Slf4j
public class MyInterceptor extends HandlerInterceptorAdapter {
    public MyInterceptor() {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("MyInterceptor afterCompletion");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor preHandle");
        log.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AuthZ authZ = method.getAnnotation(AuthZ.class);

        if (hasLogin(request) && (authZ == null || authZ.role().equals("cat"))) {
            return true;
        }

        response.setStatus(403);
        return false;
    }

    private boolean hasLogin(HttpServletRequest request) {
        return true;
    }

}