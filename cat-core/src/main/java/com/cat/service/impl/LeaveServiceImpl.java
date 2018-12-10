package com.cat.service.impl;

import com.cat.constant.enums.ProcessKeyEnum;
import com.cat.constant.enums.ProcessStatusEnum;
import com.cat.model.UserLeaveRecordDO;
import com.cat.service.ActivitiService;
import com.cat.service.LeaveRecordService;
import com.cat.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRecordService recordService;

    @Autowired
    private ActivitiService activitiService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startLeaveFlow(UserLeaveRecordDO leaveRecordDO) {
        String instanceId = activitiService.startProcessByKey(ProcessKeyEnum.LEAVE_PROCESS);
        Task task = getCurTask(instanceId);
        ProcessStatusEnum curStatus = ProcessStatusEnum.valueOf(task.getTaskDefinitionKey());

        leaveRecordDO.setInstanceId(instanceId);
        leaveRecordDO.setTaskId(task.getId());
        leaveRecordDO.setContent(task.getName());
        leaveRecordDO.setStatusCode(ProcessStatusEnum.APPLY_LEAVE.getCode());
        leaveRecordDO.setNextStatus(curStatus.getCode());

        leaveRecordDO.setResult(2);
        int id = recordService.insertRecord(leaveRecordDO);
        log.info("recordId="+ id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeLeaveFlow(Long applyId, Integer agree) {
        if (agree.equals(1)) {
            onAgree(applyId);
        } else {
            onReject(applyId);
        }

    }


    private boolean onAgree(Long applyId) {
        UserLeaveRecordDO recordDO = recordService.getRecordById(applyId);
        Task curTask = getCurTask(recordDO.getInstanceId());
        if (curTask == null) {
            log.info("工作流已完成，无下一节点。applyId=" + applyId);
            return true;
        }

        activitiService.completeTask(curTask.getId(), "YES");
        Integer curStatus =ProcessStatusEnum.valueOf(curTask.getTaskDefinitionKey()).getCode();

        recordDO.setStatusCode(curStatus);
        if (!curStatus.equals(ProcessStatusEnum.BOSS_SIGN.getCode())) {
            recordDO.setResult(2);
        } else {
            recordDO.setResult(1);
        }
        recordService.updateById(recordDO);


        UserLeaveRecordDO traceParam = new UserLeaveRecordDO();
        traceParam.setId(recordDO.getId());
        traceParam.setContent(curTask.getName());
        traceParam.setStatusCode(curStatus);
        Task nextTask = getCurTask(recordDO.getInstanceId());
        if (nextTask != null) {
            ProcessStatusEnum nextStatusEnum = ProcessStatusEnum.valueOf(nextTask.getTaskDefinitionKey());
            traceParam.setNextStatus(nextStatusEnum.getCode());
        }
        traceParam.setSignResult("同意");
        recordService.insertRecordTrace(traceParam) ;

        return true;
    }

    private boolean onReject(Long applyId) {
        UserLeaveRecordDO recordDO = recordService.getRecordById(applyId);
        Task curTask = getCurTask(recordDO.getInstanceId());
        if (curTask == null) {
            log.info("工作流已完成，无下一节点。applyId=" + applyId);
            return true;
        }


        activitiService.completeTask(curTask.getId(), "NO");

        recordDO.setStatusCode(recordDO.getStatusCode());
        recordDO.setResult(0);

        recordService.updateById(recordDO);

        UserLeaveRecordDO traceParam = new UserLeaveRecordDO();
        traceParam.setId(recordDO.getId());
        Task nextTask = getCurTask(recordDO.getInstanceId());
        if (nextTask != null) {
            ProcessStatusEnum curStatusEnum = ProcessStatusEnum.valueOf(nextTask.getTaskDefinitionKey());
            traceParam.setNextStatus(curStatusEnum.getCode());
        }
        traceParam.setContent(curTask.getName());
        traceParam.setStatusCode(recordDO.getStatusCode());
        traceParam.setSignResult("拒绝");
        recordService.insertRecordTrace(traceParam) ;

        return true;
    }





    private Task getCurTask(String instanceId) {
        List<Task> tasks = activitiService.getTaskListByInstanceId(instanceId);
        Iterator<Task> taskIterator = tasks.iterator();
        if (taskIterator.hasNext()) {
            return taskIterator.next();
        }
        return null;
    }





}
