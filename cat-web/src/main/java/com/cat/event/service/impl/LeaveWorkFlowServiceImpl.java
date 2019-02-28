package com.cat.event.service.impl;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import com.cat.event.service.LeaveRecordService;
import com.cat.event.service.LeaveWorkFlowService;
import com.cat.model.UserLeaveRecordDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccessor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 * 当前只有一个 StateMachineService，可以使用statemachineFactory，配置多个流程，
 **/
@Slf4j
@Service
public class LeaveWorkFlowServiceImpl implements LeaveWorkFlowService {
    @Autowired
    private StateMachineService leaveStateMachineService;
    @Autowired
    private LeaveRecordService leaveRecordService;
    @Autowired
    private StateMachineRuntimePersister<ProcessStatusEnum, LeaveEventEnum, String> stateMachineRuntimePersister;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean doEvent(LeaveEvent leaveEvent) {
        Assert.notNull(leaveEvent, "orderEvent 不能为空");
        Long applyId = leaveEvent.getLeaveId();

        UserLeaveRecordDO recordDO = leaveRecordService.getRecordById(applyId);

        String machineId = applyId.toString();

        StateMachine<ProcessStatusEnum, LeaveEventEnum> stateMachine = leaveStateMachineService.acquireStateMachine(machineId, false);
        boolean result = false;
        try {
            Message message = MessageBuilder
                    .withPayload(leaveEvent.getEventType())
                    .setHeader("_leaveEvent", leaveEvent)
                    .build();

            StateMachineAccessor<ProcessStatusEnum, LeaveEventEnum> accessor = stateMachine.getStateMachineAccessor();

//            // 重置状态机状态-如果不重置，什么样？
//            accessor.doWithAllRegions(function -> function.resetStateMachine(
//                    new DefaultStateMachineContext(ProcessStatusEnum.getByCode(recordDO.getStatusCode()), message.getPayload(), message.getHeaders(), null)));

            // 利用拦截器存储异常
            accessor.withRegion().addStateMachineInterceptor(
                    new StateMachineInterceptorAdapter() {
                        @Override
                        public Exception stateMachineError(StateMachine stateMachine, Exception exception) {
                            stateMachine.getExtendedState().getVariables().put("STATE_MACHINE_EXCEPTION", exception);
                            return exception;
                        }
            });

            // 执行事件
            stateMachine.start();

            result = stateMachine.sendEvent(message);

            if (stateMachine.hasStateMachineError()) {
                throw (Exception) stateMachine.getExtendedState().getVariables().get("STATE_MACHINE_EXCEPTION");
            }

            // 当前状态不接受此事件
            if (!result) {
                throw new Exception("状态异常~~");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();

        } finally {
            leaveStateMachineService.releaseStateMachine(machineId, true);
        }
        return result;
    }
}
