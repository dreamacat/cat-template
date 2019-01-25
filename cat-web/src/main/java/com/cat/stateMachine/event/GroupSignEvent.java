package com.cat.stateMachine.event;

import com.cat.enums.LeaveEvents;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
public class GroupSignEvent extends LeaveEvent {

    public GroupSignEvent(Long leaveId, boolean agree) {
        super(leaveId, agree ? LeaveEvents.GROUP_ALLOW : LeaveEvents.GROUP_REJECT);
    }
}
