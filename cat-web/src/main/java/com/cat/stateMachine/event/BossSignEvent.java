package com.cat.stateMachine.event;

import com.cat.enums.LeaveEvents;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
public class BossSignEvent extends LeaveEvent {

    public BossSignEvent(Long leaveId, boolean agree) {
        super(leaveId, agree ? LeaveEvents.BOSS_ALLOW : LeaveEvents.BOSS_REJECT);
    }
}
