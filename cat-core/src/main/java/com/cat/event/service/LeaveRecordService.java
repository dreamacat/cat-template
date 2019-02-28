package com.cat.event.service;

import com.cat.model.UserLeaveRecordDO;
import java.util.List;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
public interface LeaveRecordService {
    List<UserLeaveRecordDO> getRecordByTaskId(String taskId);

    List<UserLeaveRecordDO> getRecordByUserId(String userId);

    UserLeaveRecordDO getRecordById(Long applyId);

    boolean updateById(UserLeaveRecordDO recordDO);

    int insertRecord(UserLeaveRecordDO recordDO);

    int insertRecordTrace(UserLeaveRecordDO param);
}
