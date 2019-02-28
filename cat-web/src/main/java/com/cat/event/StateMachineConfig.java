package com.cat.event;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.action.ApplyFinishAction;
import com.cat.event.action.BossAllowAction;
import com.cat.event.action.BossRejectAction;
import com.cat.event.action.CreateLeaveAction;
import com.cat.event.action.GroupAllowAction;
import com.cat.event.action.GroupRejectAction;
import com.cat.event.listener.NewLeaveStateListener;
import java.util.EnumSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Configuration
//@EnableStateMachine
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<ProcessStatusEnum, LeaveEventEnum> {
    @Autowired
    CreateLeaveAction createLeaveAction;
    @Autowired
    GroupAllowAction groupAllowAction;
    @Autowired
    GroupRejectAction groupRejectAction;
    @Autowired
    BossAllowAction bossAllowAction;
    @Autowired
    BossRejectAction bossRejectAction;
    @Autowired
    ApplyFinishAction applyFinishAction;

    @Autowired
    @Qualifier("leaveStateMachineRuntimePersister")
    private StateMachineRuntimePersister<ProcessStatusEnum, LeaveEventEnum, String> leaveStateMachineRuntimePersister;


    @Override
    public void configure(StateMachineStateConfigurer<ProcessStatusEnum, LeaveEventEnum> states) throws Exception {
        states.withStates()
                .initial(ProcessStatusEnum.APPLY_LEAVE)
                .states(EnumSet.allOf(ProcessStatusEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ProcessStatusEnum, LeaveEventEnum> transitions) throws Exception {
        //申请请假
        transitions.withExternal().source(ProcessStatusEnum.APPLY_LEAVE).target(ProcessStatusEnum.GROUP_SIGN)
                .event(LeaveEventEnum.APPLY_LEAVE).action(createLeaveAction).and();
        //组长同意审批
        transitions.withExternal().source(ProcessStatusEnum.GROUP_SIGN).target(ProcessStatusEnum.BOSS_SIGN)
                .event(LeaveEventEnum.GROUP_ALLOW).action(groupAllowAction).and();
        //组长否决审批
        transitions.withExternal().source(ProcessStatusEnum.GROUP_SIGN).target(ProcessStatusEnum.APPLY_CLOSE)
                .event(LeaveEventEnum.GROUP_REJECT).action(groupRejectAction).action(applyFinishAction);
        //boss同意审批
        transitions.withExternal().source(ProcessStatusEnum.BOSS_SIGN).target(ProcessStatusEnum.APPLY_CLOSE)
                .event(LeaveEventEnum.BOSS_ALLOW).action(bossAllowAction).action(applyFinishAction);
        //boss否决审批
        transitions.withExternal().source(ProcessStatusEnum.BOSS_SIGN).target(ProcessStatusEnum.APPLY_CLOSE)
                .event(LeaveEventEnum.BOSS_REJECT).action(bossRejectAction).action(applyFinishAction);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ProcessStatusEnum, LeaveEventEnum> config) throws Exception {
        config.withConfiguration().listener(listener())
                .and()
                .withPersistence()
                .runtimePersister(leaveStateMachineRuntimePersister)

        ;
    }

    @Bean
    public StateMachineListener<ProcessStatusEnum, LeaveEventEnum> listener() {
        return new NewLeaveStateListener();
    }

    @Bean(name = "leaveStateMachineService")
    public StateMachineService getLeaveStateMachineService(StateMachineFactory leaveStateMachineFactory, StateMachineRuntimePersister<ProcessStatusEnum, LeaveEventEnum, String> leaveStateMachineRuntimePersister) {
        return new DefaultStateMachineService(leaveStateMachineFactory, leaveStateMachineRuntimePersister);
    }

}
