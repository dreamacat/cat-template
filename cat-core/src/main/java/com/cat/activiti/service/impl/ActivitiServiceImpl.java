package com.cat.activiti.service.impl;

import com.cat.activiti.JumpCmd;
import com.cat.activiti.service.ActivitiService;
import com.cat.constant.enums.ProcessKeyEnum;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
@Slf4j
@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ManagementService managementService;

    private HistoryService historyService;

    private final String checkVariables = "check";


    @Override
    public String startProcessByKey(ProcessKeyEnum keyEnum) {
        return startProcessByKey(keyEnum, null);
    }

    @Override
    public String startProcessByKey(ProcessKeyEnum keyEnum, String flag) {
        String processKey = keyEnum.getKey();
        Map<String, Object> variableMap = null;
        if (flag != null) {
            variableMap = Maps.newHashMap();
            variableMap.put(checkVariables, flag);
        }
        log.info("工作流开始，processKey = " + processKey);
        return runtimeService.startProcessInstanceByKey(processKey, processKey, variableMap).getId();
    }

    @Override
    public Task findTaskByInstanceId(String instanceId) {
        return taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
    }
    @Override
    public List<Task> getTaskListByInstanceId(String instanceId) {
        List<Task> tasks = ((TaskQuery)this.taskService.createTaskQuery().processInstanceId(instanceId)).list();
        return tasks;
    }

    @Override
    public Task findTaskByTaskId(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public List<Task> findTaskByGroup(List<String> groups) {
        return taskService.createTaskQuery().taskCandidateGroupIn(groups).list();
    }

    @Override
    public void completeTask(String taskId, Boolean flag) {
        Map<String, Object> variableMap = null;
        if (flag != null) {
            variableMap = new HashMap<>();
            variableMap.put(checkVariables, flag);
        }
        taskService.complete(taskId, variableMap);
        log.info("工作流完成");
    }

    @Override
    public void completeTask(String taskId, String flag) {
        Map<String, Object> variableMap = null;
        if (flag != null) {
            variableMap = new HashMap<>();
            variableMap.put(checkVariables, flag);
        }
        taskService.complete(taskId, variableMap);
        log.info("工作流完成");

    }

    @Override
    public void completeTask(String taskId) {
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put(checkVariables, true);
        taskService.complete(taskId, variableMap);
        log.info("工作流完成");

    }

    @Override
    public void deleteTask(String instanceId, String taskId, String reason) {
        try {
            runtimeService.deleteProcessInstance(instanceId, reason);
            taskService.deleteTask(taskId, reason);
        } catch (Exception e) {
            log.warn("删除任务失败，instance id: " + instanceId + ", task id:" + taskId);
            throw new RuntimeException("删除任务失败：" +instanceId );
        }
    }

    @Override
    public void deleteProcessIns(String instanceId, String reason) {
        try {
            runtimeService.deleteProcessInstance(instanceId, reason);
        } catch (Exception e) {
            log.warn("删除任务失败，instance id: " + instanceId);
            throw new RuntimeException("删除任务失败：" +instanceId );
        }
    }


    /**
     *  回滚activiti 到执行的状态
     *
     * @param processInstanceId acitivi 实例ID
     * @param  targetFlowNodeId   状态Key  orderStatusEnum.name()
     */
    @Override
    public void taskRollback(String processInstanceId, String targetFlowNodeId){

        //获取流程实例
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        Task curTask = this.findTaskByInstanceId(instance.getProcessInstanceId());

        jump2TargetFlowNode(curTask.getId(), targetFlowNodeId);

        Task taskask = this.findTaskByInstanceId(instance.getProcessInstanceId());
        System.out.println(taskask.getTaskDefinitionKey());

        throw new RuntimeException("test");

    }



    /**
     * 跳转到指定流程节点
     *
     * @param curTaskId
     * @param targetFlowNodeId
     *            指定的流程节点ID 比如跳转<endEvent id="endevent1" name="End"></endEvent> ，则targetFlowNodeId为endevent1
     */
    public  void jump2TargetFlowNode(String curTaskId, String targetFlowNodeId) {
        managementService.executeCommand(new JumpCmd(curTaskId, targetFlowNodeId));
    }
}
