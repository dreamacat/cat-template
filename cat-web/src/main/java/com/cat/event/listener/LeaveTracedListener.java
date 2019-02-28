package com.cat.event.listener;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import org.springframework.statemachine.state.State;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
public class LeaveTracedListener implements LeaveStateListener {

    @Override
    public void stateChanged(LeaveEvent leaveEvent, State<ProcessStatusEnum, LeaveEventEnum> from, State<ProcessStatusEnum, LeaveEventEnum> to) {
        System.out.println("LeaveTracedListener ===== state Changed");
    }
}
