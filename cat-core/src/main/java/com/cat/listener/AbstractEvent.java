package com.cat.listener;

import com.cat.constant.enums.EventTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.Ordered;

import java.io.Serializable;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Data
@Slf4j
public class AbstractEvent<T> implements Serializable, Ordered {
    private T data = null;
    private EventTypeEnum typeEnum;
    private String traceId;
    private final long timestamp = System.currentTimeMillis();

    /**
     * 事务提交后 注册事件执行的顺序，越小越靠前，默认是 Integer.MAX_VALUE
     */
    @Override
    public int getOrder() {
        return 1000;
    }

    /**
     *
     * @param data
     * @param typeEnum 事件类型，not null
     */
    public AbstractEvent(T data, EventTypeEnum typeEnum) {
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