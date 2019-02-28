package com.cat.event.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.service.LeaveRecordService;
import com.cat.event.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Component
public class StatusChangedAction extends AbstractAction<LeaveEvent> {
    @Autowired
    LeaveService leaveService;
    @Autowired
    LeaveRecordService recordService;

    @Override
    protected void doAction(LeaveEvent event, ProcessStatusEnum source, ProcessStatusEnum target) {
        System.out.println("状态变化...，leaveId = "+ event.getLeaveId());
        System.out.println("状态变化...，eventType = "+ event.getEventType().name());
    }

}
