package com.cat.event.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import com.cat.event.service.LeaveRecordService;
import com.cat.event.service.LeaveService;
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
        if (event.getEventType().equals(LeaveEventEnum.BOSS_ALLOW)) {
            System.out.println("同意请假，审批已结束...");
        }
        if (event.getEventType().equals(LeaveEventEnum.BOSS_REJECT)) {
            System.out.println("Boss否决，审批已结束...");
        }
        if (event.getEventType().equals(LeaveEventEnum.GROUP_REJECT)) {
            System.out.println("组长否决请假，审批已结束...");
        }
    }

}
