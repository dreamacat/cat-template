package com.cat.algorithm.dynamicProxy;

import com.cat.annotations.AopTestAnno;
import com.cat.annotations.AuthZ;

/**
 * @author wangxiaoqiang
 * @since 2018/10/29
 **/
public class RealSubject implements Subject {
    @Override
    @AuthZ
    public String rent() {
        return "rent";
    }

    @Override
    @AopTestAnno
    public String hello(String s) {
        return s;
    }
}
