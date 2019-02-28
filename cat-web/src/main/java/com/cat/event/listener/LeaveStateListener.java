package com.cat.event.listener;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.LeaveEvent;
import com.cat.event.LeaveEventEnum;
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

public interface LeaveStateListener extends StateMachineListener<ProcessStatusEnum, LeaveEventEnum> {


    @Override
    default void stateContext(StateContext<ProcessStatusEnum, LeaveEventEnum> stateContext) {
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
    default void stateChanged(LeaveEvent leaveEvent, State<ProcessStatusEnum, LeaveEventEnum> from, State<ProcessStatusEnum, LeaveEventEnum> to) {

    }


    @Override
    default void transitionStarted(Transition<ProcessStatusEnum, LeaveEventEnum> transition) {

    }


    @Override
    default void transition(Transition<ProcessStatusEnum, LeaveEventEnum> transition) {

    }


    @Override
    default void stateExited(State<ProcessStatusEnum, LeaveEventEnum> state) {

    }
    @Override
    default void stateEntered(State<ProcessStatusEnum, LeaveEventEnum> state) {
    }


    @Override
    default void stateChanged(State<ProcessStatusEnum, LeaveEventEnum> from, State<ProcessStatusEnum, LeaveEventEnum> to) {
    }


    @Override
    default void transitionEnded(Transition<ProcessStatusEnum, LeaveEventEnum> transition) {

    }



    @Override
    default void eventNotAccepted(Message<LeaveEventEnum> event) {
    }

    @Override
    default void stateMachineStarted(StateMachine<ProcessStatusEnum, LeaveEventEnum> stateMachine) {

    }

    @Override
    default void stateMachineStopped(StateMachine<ProcessStatusEnum, LeaveEventEnum> stateMachine) {

    }

    @Override
    default void stateMachineError(StateMachine<ProcessStatusEnum, LeaveEventEnum> stateMachine, Exception exception) {

    }

    @Override
    default void extendedStateChanged(Object key, Object value) {

    }

}
