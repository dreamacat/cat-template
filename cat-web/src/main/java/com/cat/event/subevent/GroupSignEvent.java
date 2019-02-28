package com.cat.event.subevent;

import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
@Data
public class GroupSignEvent extends LeaveEvent {

    private String comment;

    public GroupSignEvent(Long leaveId, boolean agree) {
        super(leaveId, agree ? LeaveEventEnum.GROUP_ALLOW : LeaveEventEnum.GROUP_REJECT);
    }

    public GroupSignEvent() {

    }
}
