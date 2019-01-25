package com.cat.stateMachine.listener;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.enums.LeaveEvents;
import com.cat.stateMachine.event.LeaveEvent;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author wangxiaoqiang
 * @since 2019/01/25
 **/

public interface LeaveStateListener extends StateMachineListener<ProcessStatusEnum, LeaveEvents> {


    @Override
    default void stateContext(StateContext<ProcessStatusEnum, LeaveEvents> stateContext) {
        // 如果为空 应该是 状态机 start 或者 stop
        if (stateContext.getSource() == null || stateContext.getTarget() == null) {
            return;
        }
        // stateContext 会被多次回调，只在  STATE_CHANGED 时处理
        if (stateContext.getStage() == StateContext.Stage.STATE_CHANGED) {
            LeaveEvent taskInfo = stateContext.getMessage().getHeaders().get("_leaveEvent", LeaveEvent.class);
            stateChanged(taskInfo, stateContext.getSource(), stateContext.getTarget());
            return;
        }
    }

    /**
     * 状态变更
     */
    default void stateChanged(LeaveEvent leaveEvent, State<ProcessStatusEnum, LeaveEvents> from, State<ProcessStatusEnum, LeaveEvents> to) {

    }


    @Override
    default void transitionStarted(Transition<ProcessStatusEnum, LeaveEvents> transition) {

    }


    @Override
    default void transition(Transition<ProcessStatusEnum, LeaveEvents> transition) {

    }


    @Override
    default void stateExited(State<ProcessStatusEnum, LeaveEvents> state) {

    }
    @Override
    default void stateEntered(State<ProcessStatusEnum, LeaveEvents> state) {
    }


    @Override
    default void stateChanged(State<ProcessStatusEnum, LeaveEvents> from, State<ProcessStatusEnum, LeaveEvents> to) {
    }


    @Override
    default void transitionEnded(Transition<ProcessStatusEnum, LeaveEvents> transition) {

    }



    @Override
    default void eventNotAccepted(Message<LeaveEvents> event) {
    }

    @Override
    default void stateMachineStarted(StateMachine<ProcessStatusEnum, LeaveEvents> stateMachine) {

    }

    @Override
    default void stateMachineStopped(StateMachine<ProcessStatusEnum, LeaveEvents> stateMachine) {

    }

    @Override
    default void stateMachineError(StateMachine<ProcessStatusEnum, LeaveEvents> stateMachine, Exception exception) {

    }

    @Override
    default void extendedStateChanged(Object key, Object value) {

    }

}
