package com.cat.service;

import com.cat.stateMachine.event.LeaveEvent;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
public interface LeaveWorkFlowService {
    /**
     * 执行或者完成一个事件
     */
    void doEvent(LeaveEvent orderEvent);
}
