package com.cat;

import com.alibaba.fastjson.JSON;
import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.event.service.LeaveRecordService;
import com.cat.model.UserLeaveRecordDO;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaveRecordServiceTest {

    private static String user1= "user1";
    private static String user2= "user2";
    private static String user3= "user3";

    @Autowired
    private LeaveRecordService recordService;

    @Test
    public void test() {
        insertRecord(user2);
        getRecordByTaskId("testTaskId");
    }

    private void insertRecord(String userId) {
        UserLeaveRecordDO recordDO = new UserLeaveRecordDO();
        recordDO.setUserId(userId);
        recordDO.setTaskId("testTaskId");
        recordDO.setStatusCode(ProcessStatusEnum.GROUP_SIGN.getCode());
//        recordDO.setNextStatus(ProcessStatusEnum.APPLY_LEAVE.getCode());
        recordService.insertRecord(recordDO);
    }

    private void getRecordByTaskId(String taskId) {
        List<UserLeaveRecordDO> recordDO = recordService.getRecordByTaskId(taskId);
        System.out.println(JSON.toJSONString(recordDO));
    }

}
