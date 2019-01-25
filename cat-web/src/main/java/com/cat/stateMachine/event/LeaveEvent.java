package com.cat.stateMachine.event;

import com.cat.enums.LeaveEvents;
import java.io.Serializable;
import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Data
public class LeaveEvent implements Serializable {
    private Long leaveId;
    private String userId;
    private LeaveEvents eventType;

    public LeaveEvent(Long leaveId, LeaveEvents eventType) {
        this.leaveId = leaveId;
        this.eventType = eventType;
    }
}
