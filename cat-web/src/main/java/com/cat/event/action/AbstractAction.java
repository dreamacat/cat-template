package com.cat.event.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
public abstract class AbstractAction<Event extends LeaveEvent> implements Action<ProcessStatusEnum, LeaveEventEnum> {

    @Override
    public void execute(StateContext<ProcessStatusEnum, LeaveEventEnum> context) {
        try {
            LeaveEvent event = (LeaveEvent) context.getMessageHeader("_leaveEvent");
            doAction((Event) event, context.getTransition().getSource().getId(), context.getTransition().getTarget().getId());
        } catch (Exception e) {
            context.getStateMachine().setStateMachineError(e);
        }

    }

    protected abstract void doAction(Event event, ProcessStatusEnum source, ProcessStatusEnum target);

}
