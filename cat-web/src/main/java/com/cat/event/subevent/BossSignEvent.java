package com.cat.event.subevent;

import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
@Data
public class BossSignEvent extends LeaveEvent {

    private String comment;

    public BossSignEvent(Long leaveId, boolean agree) {
        super(leaveId, agree ? LeaveEventEnum.BOSS_ALLOW : LeaveEventEnum.BOSS_REJECT);
    }

    public BossSignEvent() {

    }
}
