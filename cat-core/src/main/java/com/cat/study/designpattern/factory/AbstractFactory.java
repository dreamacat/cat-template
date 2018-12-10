package com.cat.study.designpattern.factory;

/**
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public abstract class AbstractFactory {
    public abstract <T extends AbstractPhone> T createPhone(Class<T> c);
}
