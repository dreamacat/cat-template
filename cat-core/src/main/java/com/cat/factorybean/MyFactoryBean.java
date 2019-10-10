package com.cat.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wangxiaoqiang
 * @since 2019/05/07
 **/
public class MyFactoryBean<T> implements FactoryBean<T> {
    @Override
    public T getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
