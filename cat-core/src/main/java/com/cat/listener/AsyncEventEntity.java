package com.cat.listener;

import com.cat.constant.enums.EventTypeEnum;

/**
 * @author wangxiaoqiang
 * @since 2018/10/08
 **/
public class AsyncEventEntity<T> extends AbstractEvent {

    public AsyncEventEntity(T data, EventTypeEnum typeEnum) {
        super(data, typeEnum);
    }
}
