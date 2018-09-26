package com.cat.utils;

import com.cat.constant.enums.EventTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Data
@Slf4j
public class EventEntity<T> {
    private T data = null;
    private EventTypeEnum typeEnum;
    private String traceId;

    public EventEntity(T data, EventTypeEnum typeEnum) {
        this.data = data;
        this.typeEnum = typeEnum;
        this.setupTraceId();
    }



    private void setupTraceId() {
        try {
            try {
                this.traceId = "trace_" + System.currentTimeMillis();
            } catch (Throwable var2) {
                this.traceId = "trace_" + System.currentTimeMillis();
            }
        } catch (Throwable var3) {
            log.warn("traceId生成异常");
        }

    }

}