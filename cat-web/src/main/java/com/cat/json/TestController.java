package com.cat.json;

import com.cat.annotations.View;
import com.cat.enums.Events;
import com.cat.enums.States;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
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
public class TestController {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @RequestMapping(value={"/testMachine"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="启动状态机 ", notes="")
    public void hello(@ApiParam(name="name", value="姓名") @RequestParam(name = "name") String name) {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}