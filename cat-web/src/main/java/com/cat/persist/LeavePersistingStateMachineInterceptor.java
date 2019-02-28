package com.cat.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.AbstractPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author wxq
 */
@Component("leaveStateMachineRuntimePersister")
public class LeavePersistingStateMachineInterceptor<S, E, T> extends AbstractPersistingStateMachineInterceptor<S, E, T>
        implements StateMachineRuntimePersister<S, E, T> {

    @Autowired
    @Qualifier("leaveStateMachinePersist")
    private StateMachinePersist orderStateMachinePersist;

    @Override
    public void write(StateMachineContext<S, E> context, T contextObj) throws Exception {
        orderStateMachinePersist.write(context,contextObj);
    }

    @Override
    public StateMachineContext<S, E> read(T contextObj) throws Exception {
        return orderStateMachinePersist.read(contextObj);
    }

    @Override
    public StateMachineInterceptor<S, E> getInterceptor() {
        return this;
    }
}
