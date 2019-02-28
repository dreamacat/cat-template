package com.cat.json;

import com.cat.annotations.View;
import com.cat.event.service.LeaveWorkFlowService;
import com.cat.event.subevent.BossSignEvent;
import com.cat.event.subevent.GroupSignEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/

@View
@Api(value = "TestApi", description = "testApi")
@Slf4j
public class TestController implements InitializingBean {

    @Autowired
    private LeaveWorkFlowService workFlowService;

    @RequestMapping(value={"/startMachine"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="启动状态机 ", notes="")
    public void startMachine(@ApiParam(name="userId", value="userId") @RequestParam(name = "userId", required = true) String userId) {

//        String machineId = userId;
//        StateMachine<ProcessStatusEnum, LeaveEvents> stateMachine = leaveStateMachineService.acquireStateMachine(machineId, true);
//        stateMachine.start();
//        stateMachine.sendEvent(LeaveEvents.APPLY_LEAVE);
    }


    @RequestMapping(value={"/testMachine"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="测试状态机 ", notes="")
    public void hello(@ApiParam(name="applyId", value="applyId") @RequestParam(name = "applyId", required = true) Long applyId) {

        workFlowService.doEvent(new GroupSignEvent(applyId, true));

        BossSignEvent bossSignEvent = new BossSignEvent(applyId, false);
        bossSignEvent.setComment("看不惯你");
        workFlowService.doEvent(bossSignEvent);

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}