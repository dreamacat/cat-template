package com.cat.event.service;

import com.cat.event.LeaveEvent;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
public interface LeaveWorkFlowService {
    /**
     * 执行或者完成一个事件
     */
    boolean doEvent(LeaveEvent orderEvent);
}
