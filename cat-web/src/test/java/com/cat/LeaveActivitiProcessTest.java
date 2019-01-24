package com.cat;

import com.cat.activiti.service.ActivitiService;
import com.cat.constant.enums.ProcessKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
public class LeaveActivitiProcessTest {

    private static String user1= "user1";
    private static String user2= "user2";
    private static String user3= "user3";
    @Autowired
    private ActivitiService activitiService;

    public void testLeaveProcess() {
        applyLeave(user1);
    }

    private void applyLeave(String userId) {
        String instanceId = activitiService.startProcessByKey(ProcessKeyEnum.LEAVE_PROCESS);
        updateApplyTaskInfo(instanceId);
    }

    private void updateApplyTaskInfo(String instanceId) {

    }

}
