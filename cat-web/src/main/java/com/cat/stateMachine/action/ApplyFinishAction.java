package com.cat.stateMachine.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.enums.LeaveEvents;
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
public class ApplyFinishAction extends AbstractAction<LeaveEvent> {
    @Autowired
    LeaveService leaveService;
    @Autowired
    LeaveRecordService recordService;

    @Override
    protected void doAction(LeaveEvent event, ProcessStatusEnum source, ProcessStatusEnum target) {
        if (event.getEventType().equals(LeaveEvents.BOSS_ALLOW)) {
            System.out.println("同意请假，审批已结束...");
        }
        if (event.getEventType().equals(LeaveEvents.BOSS_REJECT)) {
            System.out.println("Boss否决，审批已结束...");
        }
        if (event.getEventType().equals(LeaveEvents.GROUP_REJECT)) {
            System.out.println("组长否决请假，审批已结束...");
        }
    }

}
