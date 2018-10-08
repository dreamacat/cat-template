package com.cat.listener;

import com.cat.constant.enums.EventTypeEnum;

/**
 * @author wangxiaoqiang
 * @since 2018/10/08
 **/
public class SyncEventEntity<T> extends AbstractEvent {

    public SyncEventEntity(T data, EventTypeEnum typeEnum) {
        super(data, typeEnum);
    }
}
