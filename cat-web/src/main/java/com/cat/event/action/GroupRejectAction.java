package com.cat.event.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.service.LeaveRecordService;
import com.cat.event.service.LeaveService;
import com.cat.event.subevent.GroupSignEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Component
public class GroupRejectAction extends AbstractAction<LeaveEvent> {
    @Autowired
    LeaveService leaveService;
    @Autowired
    LeaveRecordService recordService;

    @Override
    protected void doAction(LeaveEvent event, ProcessStatusEnum source, ProcessStatusEnum target) {
        GroupSignEvent groupSignEvent = (GroupSignEvent) event;
        System.out.println("组长审批未通过...");
        System.out.println("审批备注：" + groupSignEvent.getComment());

    }


}
