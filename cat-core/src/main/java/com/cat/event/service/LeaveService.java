package com.cat.event.service;

import com.cat.model.UserLeaveRecordDO;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
public interface LeaveService {



    void startLeaveFlow(UserLeaveRecordDO leaveRecordDO);

    void completeLeaveFlow(Long applyId, Integer agree);

    void goToStatus(UserLeaveRecordDO leaveRecordDO, String status);

}
