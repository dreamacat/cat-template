package com.cat.event;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Data
public class LeaveEvent implements Serializable {
    final String eventId = UUID.randomUUID().toString();

    private Long leaveId;
    LeaveEventEnum eventType;

    public LeaveEvent(Long leaveId, LeaveEventEnum eventType) {
        this.leaveId = leaveId;
        this.eventType = eventType;
    }

    public LeaveEvent() {

    }
}
