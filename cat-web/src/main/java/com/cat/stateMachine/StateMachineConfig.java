package com.cat.stateMachine;

import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.enums.LeaveEvents;
import com.cat.stateMachine.action.BossAllowAction;
import com.cat.stateMachine.action.BossRejectAction;
import com.cat.stateMachine.action.CreateLeaveAction;
import com.cat.stateMachine.action.GroupAllowAction;
import com.cat.stateMachine.action.GroupRejectAction;
import com.cat.stateMachine.listener.LeaveStateListener;
import java.util.EnumSet;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Configuration
//@EnableStateMachine
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<ProcessStatusEnum, LeaveEvents> {
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
    @Resource
    LeaveStateListener leaveStatusChangedListener;
    @Resource
    LeaveStateListener leaveTracedListener;

    @Override
    public void configure(StateMachineStateConfigurer<ProcessStatusEnum, LeaveEvents> states) throws Exception {
        states.withStates()
                .initial(ProcessStatusEnum.APPLY_LEAVE)
                .states(EnumSet.allOf(ProcessStatusEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ProcessStatusEnum, LeaveEvents> transitions) throws Exception {
        //申请请假
        transitions.withExternal().source(ProcessStatusEnum.APPLY_LEAVE).target(ProcessStatusEnum.GROUP_SIGN)
                .event(LeaveEvents.APPLY_LEAVE).action(createLeaveAction).and();
        //组长同意审批
        transitions.withExternal().source(ProcessStatusEnum.GROUP_SIGN).target(ProcessStatusEnum.BOSS_SIGN)
                .event(LeaveEvents.GROUP_ALLOW).action(groupAllowAction).and();
        //组长否决审批
        transitions.withExternal().source(ProcessStatusEnum.GROUP_SIGN).target(ProcessStatusEnum.APPLY_CLOSE)
                .event(LeaveEvents.GROUP_REJECT).action(groupRejectAction);
        //boss同意审批
        transitions.withExternal().source(ProcessStatusEnum.BOSS_SIGN).target(ProcessStatusEnum.APPLY_CLOSE)
                .event(LeaveEvents.BOSS_ALLOW).action(bossAllowAction);
        //boss否决审批
        transitions.withExternal().source(ProcessStatusEnum.BOSS_SIGN).target(ProcessStatusEnum.APPLY_CLOSE)
                .event(LeaveEvents.BOSS_REJECT).action(bossRejectAction);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ProcessStatusEnum, LeaveEvents> config) throws Exception {
        config
                .withConfiguration()
                .listener(leaveStatusChangedListener).listener(leaveTracedListener);
    }


    @Bean(name = "leaveStateMachineService")
    public StateMachineService getLeaveStateMachineService(StateMachineFactory leaveStateMachineFactory) {
        return new DefaultStateMachineService(leaveStateMachineFactory);
    }

}
