package com.cat.event.service.impl;

import com.cat.dao.user.UserLeaveRecordDao;
import com.cat.event.service.LeaveRecordService;
import com.cat.model.UserLeaveRecordDO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Service
@Slf4j
public class LeaveRecordServiceImpl implements LeaveRecordService {

    @Autowired
    private UserLeaveRecordDao userLeaveRecordDao;


    @Override
    public List<UserLeaveRecordDO> getRecordByTaskId(String taskId) {
        UserLeaveRecordDO param = new UserLeaveRecordDO();
        param.setTaskId(taskId);
        List<UserLeaveRecordDO> recordDO = userLeaveRecordDao.findRecordList(param);
        return recordDO;
    }

    @Override
    public List<UserLeaveRecordDO> getRecordByUserId(String userId) {
        UserLeaveRecordDO param = new UserLeaveRecordDO();
        param.setUserId(userId);
        List<UserLeaveRecordDO> recordDO = userLeaveRecordDao.findRecordList(param);
        return recordDO;
    }

    @Override
    public UserLeaveRecordDO getRecordById(Long applyId) {
        UserLeaveRecordDO leaveRecordDO = userLeaveRecordDao.findById(applyId);
        return leaveRecordDO;
    }

    @Override
    @Transactional
    public boolean updateById(UserLeaveRecordDO param) {
        return userLeaveRecordDao.updateById(param) > 0;
    }

    @Override
    @Transactional
    public int insertRecord(UserLeaveRecordDO param) {
        Long id = userLeaveRecordDao.insertRecord(param);

        UserLeaveRecordDO traceParam = new UserLeaveRecordDO();
        traceParam.setId(id);
        traceParam.setContent(param.getContent());
        traceParam.setSignResult("申请");
        traceParam.setStatusCode(param.getStatusCode());
        traceParam.setNextStatus(param.getNextStatus());
        traceParam.setResult(param.reject() ? 0 : 1);
        return this.insertRecordTrace(traceParam);
    }

    @Override
    @Transactional
    public int insertRecordTrace(UserLeaveRecordDO param) {
        return userLeaveRecordDao.insertApplyTrace(param);
    }


}
