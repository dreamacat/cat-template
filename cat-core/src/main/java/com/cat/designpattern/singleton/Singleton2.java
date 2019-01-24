package com.cat.designpattern.singleton;

import com.cat.model.EntityVO;

/**
 * @author wangxiaoqiang
 * @since 2018/12/12.
 */

/**
 * 枚举实现的单例
 */
public enum Singleton2 {
    INSTANCE;

    private EntityVO entity = null;
    private Singleton2() {
        entity = new EntityVO("a", "b");
    }

    public EntityVO getInstance() {
        return entity;
    }
}
