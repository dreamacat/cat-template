package com.cat.listener;

import com.cat.constant.enums.EventTypeEnum;

/**
 * @author panke
 * @since 2018/6/5
 */
public interface EventHandler {

    /**
     * 获取监听的事件列表
     */
    EventTypeEnum[] getListenerEvents();

    /**
     * 执行事件通知
     *
     * @param event 要执行的事件对象
     */
    void doHandler(AbstractEvent event);

}
