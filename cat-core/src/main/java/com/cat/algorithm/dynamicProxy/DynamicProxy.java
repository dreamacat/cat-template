package com.cat.algorithm.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangxiaoqiang
 * @since 2018/10/29
 **/
public class DynamicProxy implements InvocationHandler {

    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before proxy");
        System.out.println("Method:" + method);
        method.invoke(subject, args);
        System.out.println("after proxy");
        return null;
    }
}
