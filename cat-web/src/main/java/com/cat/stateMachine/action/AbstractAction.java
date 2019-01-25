package com.cat.stateMachine.action;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.enums.LeaveEvents;
import com.cat.stateMachine.event.LeaveEvent;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
public abstract class AbstractAction<Event extends LeaveEvent> implements Action<ProcessStatusEnum, LeaveEvents> {

    @Override
    public void execute(StateContext<ProcessStatusEnum, LeaveEvents> context) {
        try {
            LeaveEvent event = (LeaveEvent) context.getMessageHeader("_leaveEvent");
            doAction((Event) event, context.getTransition().getSource().getId(), context.getTransition().getTarget().getId());
        } catch (Exception e) {
            context.getStateMachine().setStateMachineError(e);
        }

    }

    protected abstract void doAction(Event event, ProcessStatusEnum source, ProcessStatusEnum target);

}
