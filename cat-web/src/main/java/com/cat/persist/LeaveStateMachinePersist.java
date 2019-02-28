package com.cat.persist;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEventEnum;
import com.cat.event.service.LeaveRecordService;
import com.cat.model.UserLeaveRecordDO;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultExtendedState;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component("leaveStateMachinePersist")
public class LeaveStateMachinePersist implements StateMachinePersist<ProcessStatusEnum,LeaveEventEnum,String> {
    @Autowired
    private LeaveRecordService leaveRecordService;

    @Override
    public void write(StateMachineContext<ProcessStatusEnum, LeaveEventEnum> context, String applyId) throws Exception {
        // TODO 存储状态机状态到数据库
        ProcessStatusEnum statusEnum = context.getState();
        UserLeaveRecordDO recordDO = leaveRecordService.getRecordById(Long.valueOf(applyId));
        recordDO.setStatusCode(statusEnum.getCode());
        leaveRecordService.updateById(recordDO);
        log.info("保存状态机状态，applyId={}",applyId);

    }

    @Override
    public StateMachineContext<ProcessStatusEnum, LeaveEventEnum> read(String applyId) throws Exception {
        log.info("读取状态机状态，applyId={}",applyId);
        UserLeaveRecordDO leaveRecordDO = leaveRecordService.getRecordById(Long.valueOf(applyId));
        StateMachineContext<ProcessStatusEnum, LeaveEventEnum> stateMachineContext = new DefaultStateMachineContext<ProcessStatusEnum, LeaveEventEnum>(
                new ArrayList<StateMachineContext<ProcessStatusEnum, LeaveEventEnum>>(),
                ProcessStatusEnum.getByCode(leaveRecordDO.getStatusCode()),
                null, null,
                new DefaultExtendedState(),
                new HashMap<ProcessStatusEnum, ProcessStatusEnum>(), applyId);
        return stateMachineContext;
    }

}