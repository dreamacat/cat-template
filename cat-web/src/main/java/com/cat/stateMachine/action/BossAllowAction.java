package com.cat.stateMachine.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.service.LeaveRecordService;
import com.cat.service.LeaveService;
import com.cat.stateMachine.event.LeaveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Component
public class BossAllowAction extends AbstractAction<LeaveEvent> {
    @Autowired
    LeaveService leaveService;
    @Autowired
    LeaveRecordService recordService;

    @Override
    protected void doAction(LeaveEvent event, ProcessStatusEnum source, ProcessStatusEnum target) {
        System.out.println("Boss审批通过...");
    }

}
