package com.cat.event.listener;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import com.cat.event.action.StatusChangedAction;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/
@Slf4j
public class NewLeaveStateListener extends StateMachineListenerAdapter<ProcessStatusEnum, LeaveEventEnum> {
    @Autowired
    StatusChangedAction statusChangedAction;

    @Override
    public void stateContext(StateContext<ProcessStatusEnum, LeaveEventEnum> context) {
        if (context.getStage() == StateContext.Stage.TRANSITION) {
            //屏蔽掉状态机初始化状态
            if(!Objects.isNull(context.getTransition().getSource())){
                log.info("common 执行...");
                statusChangedAction.execute(context);
            }
        }
        if(context.getStage() == StateContext.Stage.EVENT_NOT_ACCEPTED){
            LeaveEvent event = (LeaveEvent) context.getMessageHeader("_event");
            log.error("触发事件时状态非法：orderId={},状态={},event={}",event.getLeaveId(),context.getSource().getId().name(),event.getEventType().name());
            context.getStateMachine().setStateMachineError(new Exception("触发事件与订单状态不匹配"));
        }
    }




}
