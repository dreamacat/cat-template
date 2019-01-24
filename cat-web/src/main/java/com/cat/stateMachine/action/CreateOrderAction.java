package com.cat.stateMachine.action;

import com.cat.enums.Events;
import com.cat.enums.States;
import com.cat.service.LeaseCompanyService;
import com.cat.stateMachine.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Component
public class CreateOrderAction implements Action<States, Events> {
    @Autowired
    LeaseCompanyService leaseCompanyService;

    @Override
    public void execute(StateContext<States, Events> context) {
        OrderEvent orderEvent  = (OrderEvent) context.getMessageHeader("_orderEvent");
//        leaseCompanyService.getLeaseCompanyByCode(orderEvent.getOrderId());
    }
}
