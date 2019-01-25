package com.cat.stateMachine.listener;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.enums.LeaveEvents;
import com.cat.stateMachine.event.LeaveEvent;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
@Component
public class LeaveTracedListener implements LeaveStateListener {

    @Override
    public void stateChanged(LeaveEvent leaveEvent, State<ProcessStatusEnum, LeaveEvents> from, State<ProcessStatusEnum, LeaveEvents> to) {
        System.out.println("LeaveTracedListener ===== state Changed");
    }
}
