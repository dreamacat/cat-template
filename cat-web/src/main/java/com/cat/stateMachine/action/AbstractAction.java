package com.cat.stateMachine.action;

import com.cat.enums.Events;
import com.cat.enums.States;
import com.cat.stateMachine.event.OrderEvent;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
public abstract class AbstractAction<Event extends OrderEvent> implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        try {
            OrderEvent event = (OrderEvent) context.getMessageHeader("_orderEvent");
            doAction((Event) event, context.getTransition().getSource().getId(), context.getTransition().getTarget().getId());
        } catch (Exception e) {
            context.getStateMachine().setStateMachineError(e);
        }

    }

    protected abstract void doAction(Event event, States source, States target);

}
